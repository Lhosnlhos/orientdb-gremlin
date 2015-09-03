package org.apache.tinkerpop.gremlin.orientdb;

import com.orientechnologies.orient.core.db.ODatabaseFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.junit.Test;

import java.lang.System;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

public class OrientSampleTest {

    @Test
    public void labelTest() {
        String graphUri = "memory:test";
//        String graphUri = "plocal:target/graph" + Math.random();
//        String graphUri = "remote:localhost/test";
        OrientGraph graph = new OrientGraphFactory(graphUri, "root", "root").getNoTx();

        OrientVertex v1 = (OrientVertex) graph.addVertex();
        OrientVertex v2 = (OrientVertex) graph.addVertex();
        OrientEdge e = (OrientEdge) v1.addEdge("label1", v2);

        Iterator<Vertex> vertices = graph.vertices();
        while (vertices.hasNext())
            System.out.println(vertices.next());

//        OClass clazz = v1.getRawDocument().getSchemaClass();
//        Collection<OClass> subclasses = clazz.getSubclasses();
//        System.out.println("Subclasses of vertex: " + subclasses.size());
//        subclasses.forEach(c -> System.out.println(c));
    }

}