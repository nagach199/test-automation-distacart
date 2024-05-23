#./bin/bash
mvn clean test -Dtest=Runner -DENVURL=QA && mvn test -Dtest=GCPRunner