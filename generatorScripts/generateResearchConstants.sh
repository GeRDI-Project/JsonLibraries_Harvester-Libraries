#!/bin/bash

mvn exec:java -Dexec.mainClass="de.gerdiproject.generator.research.utils.ResearchGenerator"
./scripts/formatting/astyle-format.sh