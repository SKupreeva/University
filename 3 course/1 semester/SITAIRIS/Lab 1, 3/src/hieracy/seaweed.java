package hieracy;

import hieracy.bacterium;
import hieracy.mushroom;
import hieracy.plant;

public interface seaweed extends plant, bacterium, mushroom {
    void average(int a, int b);
}
