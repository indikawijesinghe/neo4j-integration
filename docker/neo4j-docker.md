# Neo4j Docker Setup
# Run Neo4j locally using Docker:

# bash

docker run -d \
  --name neo4j \
  -p 7474:7474 \
  -p 7687:7687 \
  -e NEO4J_AUTH=neo4j/12345678 \
  neo4j:5
