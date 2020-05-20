package pglp.pglp9_9;

public interface CommandeMoveForms <T> extends Commande{

	T excute(T form,int x,int Y);

}
