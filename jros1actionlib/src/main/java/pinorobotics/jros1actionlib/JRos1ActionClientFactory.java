/*
 * Copyright 2022 jrosactionlib project
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
import id.jrosclient.RosVersion;
import id.jrosmessages.Message;
import id.xfunction.Preconditions;
import pinorobotics.jros1actionlib.actionlib_msgs.Action1Definition;

/**
 * Factory methods for {@link JRos1ActionClient}
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRos1ActionClientFactory {

    /**
     * Create client for ROS1 Action Server
     *
     * @param client ROS client
     * @param actionDefinition message type definitions for an action
     * @param actionServerName name of the action server which will execute the actions
     * @param <G> message type used to represent a goal
     * @param <R> message type sent by ActionServer upon goal completion
     */
    public <G extends Message, R extends Message> JRos1ActionClient<G, R> createClient(
            JRos1Client client, Action1Definition<G, R> actionDefinition, String actionServerName) {
        Preconditions.isTrue(
                client.getSupportedRosVersion().contains(RosVersion.ROS1), "Requires ROS1 client");
        return new JRos1ActionClient<>(client, actionDefinition, actionServerName);
    }
}
