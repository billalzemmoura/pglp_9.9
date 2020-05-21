package pglp.pglp9_9;

public class CreateTriangleExc extends CreateForms implements CreateTriangle {

	public Triangle excute(String nom, Point A, Point B, Point C) {

		return new Triangle(nom, A, B, C);

	}

}
