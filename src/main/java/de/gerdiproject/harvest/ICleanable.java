/**
 * Copyright © 2017 Robin Weiss, Fidan Limani (http://www.gerdi-project.de)
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
package de.gerdiproject.harvest;

/**
 * This interface represents objects that can be cleaned up to make them compatible
 * with the ElasticSearch index.
 *
 * @author Robin Weiss
 */
public interface ICleanable
{
    /**
     * Cleans the object, making it compatible with the ElasticSearch index.
     *
     * @return true if the clean up was successful and the object is now valid
     */
    boolean clean();
}
