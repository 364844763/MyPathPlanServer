
package pathplan.heuristics;


import entity.SearchDomain;
import entity.State;


public class ManhattanDistanceHeuristics implements DistanceHeuristics {
    
    private static ManhattanDistanceHeuristics _instance = null;

    protected ManhattanDistanceHeuristics() {

    }
    

    public static ManhattanDistanceHeuristics instance() {
        if (_instance == null) {
            _instance = new ManhattanDistanceHeuristics();
        }
        return _instance;
    }
    

    @Override
    public float h(SearchDomain map, State cNode, State gNode) {
        return map.hCost(cNode, gNode);
    }
    

    @Override
    public boolean updateH(SearchDomain map, State cNode, State gNode, float hModifier) {
        return false;
    }
    


}
