# SocialNetwork
Comparing Neo4j with Postgresql when it comes to finding connection between people

# Dependencies
  * Java
  * Maven
  * IDE that supports JAVA, Netbeans, Eclipse or Intellij

# Docker & Database Setup
I used the container called "data" that is the postgresql container that we used with Jens. And for Neo4j i used the Neo4j container that Helge gave us.
Setting up Neo4j is rather simple, we can use the guide that Helge made in [Here](https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/lecture_notes/13-More%20Neo4j.ipynb)

For Postgresql there is a bit more setup, i would download the dataset once more. Because when using Helge's guide we change it which makes it not compatible with Postgresql.
When that is downloaded you need to untar it with the following command

`tar -xvf archive_graph.tar.gz`

Then we want to create our Tables and relations so we can import the data which is done by following

Start by loggin in as appdev user `psql -h localhost -U appdev`. This will make it possible to execute SQL queries.

```
CREATE TABLE Social_people(
ID bigint PRIMARY KEY,
name varchar,
job varchar,
birthday varchar
);

CREATE TABLE endorsements(
source_node_id bigint references Social_people(ID),
target_node_id bigint references Social_people(ID)
);
```

Now our Tables are ready to be populated, that is done by following

`\copy Social_people FROM '$pwd/social_network_nodes.csv' WITH CSV HEADER DELIMITER AS ',';`

This will take a little while because of the dataset size. But when that is done we are ready to run our test.

You need to import the project as a maven project in your IDE and simply run the main method. Important sidenote, if you run this
it will take some time because of the size of data.

# Results

As we can see below the complexity and time usage/resource usage on Postgresql escalates quickly because of the redundancy.
This is because we need to join everytime we want to check a reference between the 2 tables. And when this happens multiple times it becomes heavy.

On the other side we have Neo4j that is by all means a true relationship based Database. We have a graph that connects all nodes,
so we always know where our goal is. We don't have to jump back and forth between tables, instead we just follow the path until we meet our goal.
In this case it's traverse to the next node that is related by "ENDORSES" and we then just extend this from 1 jump up to 5

|   |  Postgresql |   |  Neo4j |   |
|---|---|---|---|---|
|   |  Average |Median   |  Average |Median   |
| Depth 1  | 0.4624  | 0.4578  |0.3620   | 0.366  |
| Depth 2  |  1.4083 |  1.3888 | 0.3804  | 0.385  |
| Depth 3  |  2.700 | 3.1523  |  0.4772 |  0.451 |
| Depth 4  |  3.7493 | 3.7364  | 2.7302  | 2.706  |
| Depth 5 |  11.9453 | 11.8398  | Error  | Error  |

Had a problem when running Depth 5 with Neo4j the error was related to Session not being flushed and then it timedout.
I spend around 2 hours fixing this issue both with refactoring and Searching online but couldn't fix it.
