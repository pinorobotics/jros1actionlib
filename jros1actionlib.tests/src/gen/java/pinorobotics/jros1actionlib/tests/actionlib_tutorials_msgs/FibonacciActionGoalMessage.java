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
package pinorobotics.jros1actionlib.tests.actionlib_tutorials_msgs;

import id.jros1messages.std_msgs.HeaderMessage;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.RosInterfaceType;
import id.xfunction.XJson;
import java.util.Objects;
import pinorobotics.jros1actionlib.actionlib_msgs.Action1GoalMessage;
import pinorobotics.jros1actionlib.actionlib_msgs.GoalIdMessage;

/**
 * Definition for test_server/FibonacciActionGoal ====== DO NOT MODIFY! AUTOGENERATED FROM AN ACTION
 * DEFINITION ======
 */
@MessageMetadata(
        name = FibonacciActionGoalMessage.NAME,
        fields = {"header", "goal_id", "goal"},
        md5sum = "006871c7fa1d0e3d5fe2226bf17b2a94",
        interfaceType = RosInterfaceType.ACTION)
public class FibonacciActionGoalMessage implements Action1GoalMessage<FibonacciGoalMessage> {

    static final String NAME = "actionlib_tutorials/FibonacciActionGoal";

    public HeaderMessage header = new HeaderMessage();

    public GoalIdMessage goal_id = new GoalIdMessage();

    public FibonacciGoalMessage goal = new FibonacciGoalMessage();

    public FibonacciActionGoalMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    @Override
    public FibonacciActionGoalMessage withGoalId(GoalIdMessage goal_id) {
        this.goal_id = goal_id;
        return this;
    }

    @Override
    public FibonacciActionGoalMessage withGoal(FibonacciGoalMessage goal) {
        this.goal = goal;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, goal_id, goal);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (FibonacciActionGoalMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(goal_id, other.goal_id)
                && Objects.equals(goal, other.goal);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "goal_id", goal_id,
                "goal", goal);
    }
}
