
package pathplan.heuristics;


import entity.SearchDomain;
import entity.State;


public class EuclideanDistanceHeuristics implements DistanceHeuristics {
    
    private static EuclideanDistanceHeuristics _instance = null;
    
    protected EuclideanDistanceHeuristics() {

    }
    

    public static EuclideanDistanceHeuristics instance() {
        if (_instance == null) {
            _instance = new EuclideanDistanceHeuristics();
        }
        return _instance;
    }
    

    @Override
    public float h(SearchDomain map, State cNode, State gNode) {
        // TODO Auto-generated method stub
        return 0;
    }
    

    @Override
    public boolean updateH(SearchDomain map, State cNode, State gNode, float hModifier) {
        // TODO Auto-generated method stub
        return false;
    }

}
