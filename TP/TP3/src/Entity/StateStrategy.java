package Entity;

import Infrastructures.Batiment;

public interface StateStrategy {

	public void process(Client c, Batiment b);
}
