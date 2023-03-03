/*
 * Copyright 2021 jrosactionlib project
 * 
 * Website: https://github.com/pinorobotics/jros1actionlib
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pinorobotics.jros1actionlib;

import id.jros1client.JRos1Client;
import id.jrosclient.TopicSubscriber;
import id.jrosmessages.Message;
import id.jrosmessages.primitives.Time;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.lang.XThread;
import id.xfunction.logging.XLogger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow.Subscription;
import pinorobotics.jros1actionlib.actionlib_msgs.Action1Definition;
import pinorobotics.jros1actionlib.actionlib_msgs.Action1ResultMessage;
import pinorobotics.jros1actionlib.actionlib_msgs.GoalIdMessage;
import pinorobotics.jrosactionlib.impl.AbstractJRosActionClient;
import pinorobotics.jrosactionlib.msgs.ActionGoalMessage;

/**
 * Client which allows to interact with ROS1 Action Server. It communicates with it via a "ROS
 * Action Protocol"
 *
 * @see <a href="http://wiki.ros.org/actionlib/DetailedDescription">Actionlib</a>
 * @param <G> message type used to represent a goal
 * @param <R> message type sent by ActionServer upon goal completion
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRos1ActionClient<G extends Message, R extends Message>
        extends AbstractJRosActionClient<GoalIdMessage, G, R> {

    private static final XLogger LOGGER = XLogger.getLogger(JRos1ActionClient.class);
    private TopicSubscriber<Action1ResultMessage<R>> resultsDispatcher;
    private Map<GoalIdMessage, CompletableFuture<Action1ResultMessage<R>>> pendingGoals =
            new HashMap<>();
    private int goalCounter;
    private JRos1Client client;

    /**
     * Creates a new instance of the client. Users should not call it directly but use {@link
     * JRos1ActionClientFactory} instead.
     *
     * @param client ROS1 client
     * @param actionDefinition message type definitions for an action
     * @param actionServerName name of the action server which will execute the actions
     */
    JRos1ActionClient(
            JRos1Client client, Action1Definition<G, R> actionDefinition, String actionServerName) {
        super(client, actionDefinition, actionServerName);
        this.client = client;
        resultsDispatcher =
                new TopicSubscriber<Action1ResultMessage<R>>(
                        (Class) actionDefinition.getActionResultMessage(), actionServerName) {
                    @Override
                    public void onNext(Action1ResultMessage item) {
                        LOGGER.entering("onNext " + actionServerName);
                        var future = pendingGoals.get(item.getGoalId());
                        future.complete(item);
                        // request next message
                        getSubscription().get().request(1);
                        LOGGER.exiting("onNext " + actionServerName);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        pendingGoals.values().forEach(fu -> fu.completeExceptionally(throwable));
                    }
                };
    }

    @Override
    protected void submitGoal(
            GoalIdMessage goalId, ActionGoalMessage<GoalIdMessage, G> actionGoal) {
        while (getGoalPublisher().getNumberOfSubscribers() == 0) {
            LOGGER.fine("No subscribers");
            XThread.sleep(100);
        }
        super.submitGoal(goalId, actionGoal);
    }

    @Override
    protected void onStart() throws Exception {
        client.subscribe(resultsDispatcher);
    }

    @Override
    protected void onClose() {
        resultsDispatcher.getSubscription().ifPresent(Subscription::cancel);
    }

    @Override
    protected GoalIdMessage createGoalId() {
        var id = hashCode() + "." + goalCounter++;
        return new GoalIdMessage().withId(new StringMessage(id)).withStamp(Time.now());
    }

    @Override
    protected CompletableFuture<Action1ResultMessage<R>> callGetResult(GoalIdMessage goalId) {
        var future = new CompletableFuture<Action1ResultMessage<R>>();
        pendingGoals.put(goalId, future);
        return future;
    }
}
