:: Licensed to the Apache Software Foundation (ASF) under one
:: or more contributor license agreements.  See the NOTICE file
:: distributed with this work for additional information
:: regarding copyright ownership.  The ASF licenses this file
:: to you under the Apache License, Version 2.0 (the
:: "License"); you may not use this file except in compliance
:: with the License.  You may obtain a copy of the License at
::
::   http://www.apache.org/licenses/LICENSE-2.0
::
:: Unless required by applicable law or agreed to in writing,
:: software distributed under the License is distributed on an
:: "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
:: KIND, either express or implied.  See the License for the
:: specific language governing permissions and limitations
:: under the License.

:: This Script runs the java main function of the ResearchGenerator in order to read a JSON
:: file and replace constants files, if necessary. Afterwards, all files are formatted.
::
:: Since this script requires compiled Java classes, it is required to run 'mvn clean compile'
:: or a later stage of the build lifecycle. When running 'mvn clean compile -Dgenerate', the
:: classes are compiled and the Generator is executed thereafter.

mvn exec:java -D"exec.mainClass"="de.gerdiproject.generator.research.utils.ResearchGenerator" & .\scripts\formatting\astyle-format.bat