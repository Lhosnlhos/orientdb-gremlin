package org.apache.tinkerpop.gremlin.orientdb.gremlintest;

import org.apache.commons.configuration.Configuration;
import org.apache.tinkerpop.gremlin.LoadGraphWith;
import org.apache.tinkerpop.gremlin.orientdb.OrientFactory;
import org.apache.tinkerpop.gremlin.orientdb.OrientStandardGraph;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.TransactionTest;

import java.util.Map;

import static org.apache.tinkerpop.gremlin.orientdb.OrientGraph.CONFIG_TRANSACTIONAL;
import static org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils.asList;

public class OrientStandardGraphProvider extends OrientGraphProvider {

  static {
    IGNORED_TESTS.put(TransactionTest.class, asList("shouldExecuteWithCompetingThreads"));
  }

  @Override
  public Map<String, Object> getBaseConfiguration(String graphName, Class<?> test, String testMethodName,
      LoadGraphWith.GraphData loadGraphWith) {
    Map<String, Object> baseConfiguration = super.getBaseConfiguration(graphName, test, testMethodName, loadGraphWith);
    baseConfiguration.put(CONFIG_TRANSACTIONAL, true);
    baseConfiguration.put(Graph.GRAPH, OrientFactory.class.getName());
    return baseConfiguration;
  }

  @Override
  public Graph openTestGraph(Configuration config) {
    return super.openTestGraph(config);
  }

  @Override
  public void clear(Graph graph, Configuration configuration) throws Exception {
    if (graph != null) {
      OrientStandardGraph g = (OrientStandardGraph) graph;

      if (g.isOpen()) {
        g.drop();
      }
    }
  }
}
