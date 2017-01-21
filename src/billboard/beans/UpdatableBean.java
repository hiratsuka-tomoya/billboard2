package billboard.beans;

public abstract class UpdatableBean extends Bean {

	public abstract String getSqlInsert();

	public abstract String getSqlUpdate();

}
