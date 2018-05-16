package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeVertexException;
import Exception.Vertex.VertexTypeException;
import edge.Edge;
import edge.NetworkConnection;
import vertex.*;

public class NetworkTopology extends ConcreteGraph {
    public NetworkTopology(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException {
        if (!((vertex instanceof Computer) || (vertex instanceof Router) || (vertex instanceof Server) || (vertex instanceof WirelessRouter)))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeVertexException, EdgeTypeException {
        if (!(edge instanceof NetworkConnection))
            throw new EdgeTypeException(getLabel());
        // 避免单重边中存在多充边，如果存在，就不添加这条边
        return super.edges().stream().filter(item -> item.equals(edge)).count() == 0 && super.addEdge(edge);
    }
}
