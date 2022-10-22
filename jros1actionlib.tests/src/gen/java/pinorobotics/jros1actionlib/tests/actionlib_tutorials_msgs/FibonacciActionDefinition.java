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

import pinorobotics.jros1actionlib.actionlib_msgs.Action1Definition;
import pinorobotics.jros1actionlib.actionlib_msgs.Action1GoalMessage;
import pinorobotics.jros1actionlib.actionlib_msgs.Action1ResultMessage;

public class FibonacciActionDefinition
        implements Action1Definition<FibonacciGoalMessage, FibonacciResultMessage> {

    @Override
    public Class<? extends Action1GoalMessage<FibonacciGoalMessage>> getActionGoalMessage() {
        return FibonacciActionGoalMessage.class;
    }

    @Override
    public Class<? extends Action1ResultMessage<FibonacciResultMessage>> getActionResultMessage() {
        return FibonacciActionResultMessage.class;
    }
}
