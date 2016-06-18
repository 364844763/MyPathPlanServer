
package pathplan.heuristics;

public enum HeuristicsType {
    

    MANHATTAN_DISTANCE {

        @Override
        public Heuristics instance() {
            return ManhattanDistanceHeuristics.instance();
        }
        
    },
    
    EUCLIDEAN_DISTANCE {
        
        @Override
        public Heuristics instance() {
            return EuclideanDistanceHeuristics.instance();
        }
        
    },
    ;

    public abstract Heuristics instance();

}
