/*
 * Copyright 2021 jrosactionlib project
 *
 * Website: https://github.com/pinorobotics/jrosactionlib
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
/**
 * Java module which allows to interact with <a href="http://wiki.ros.org/actionlib/">ROS1 (Robot
 * Operating System) Action Server</a>.
 *
 * <p>For usage examples see <a href="http://pinoweb.freetzi.com/jrosactionlib">Documentation</a>
 *
 * @see <a
 *     href="https://github.com/pinorobotics/jros1actionlib/blob/main/jros1actionlib/release/CHANGELOG.md">Releases</a>
 * @see <a href="https://github.com/pinorobotics/jros1actionlib">GitHub repository</a>
 * @author aeon_flux aeon_flux@eclipso.ch
 */
module jros1actionlib {
    requires transitive jrosactionlib;
    requires transitive jros1client;
    requires transitive jros1messages;
    requires id.xfunction;
    requires id.kineticstreamer;

    exports pinorobotics.jros1actionlib;
    exports pinorobotics.jros1actionlib.actionlib_msgs;
}
