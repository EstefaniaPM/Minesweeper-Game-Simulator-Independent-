import java.util.Scanner;
public class Main
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println();
		System.out.print("Inserte el numero de filas de su tablero: ");
		int tamanio=sc.nextInt();
		System.out.println();

		Tablero miTablero=new Tablero(tamanio);
		System.out.println("Hay "+miTablero.getBombas()+" bombas en el tablero");
		System.out.println();
		System.out.println(miTablero.toString());

		int op, a, b;
		while(miTablero.getJuegoTerminado()==false)
		{
			System.out.println("1. Hacer un tiro");
			System.out.println("2. Marcar/Desmarcar celda");
			System.out.println();
			System.out.print("Inserte su opcion: ");
			
			op=sc.nextInt();

			switch(op)
			{
				case 1:
					System.out.println();
					System.out.print("Inserte su coordenada separada por un espacio: ");
					a=sc.nextInt();
					b=sc.nextInt();
					if(miTablero.validaTiro(b, a)==false)
						System.out.println("Ya tiro en esa celda");
					else
						miTablero.tiro(b, a);
					break;
				case 2:
					System.out.println();
					System.out.print("Inserte su coordenada separada por un espacio: ");
					a=sc.nextInt();
					b=sc.nextInt();
					miTablero.marcarCelda(b, a);
					break;
			}
			System.out.println();
			System.out.println(miTablero.toString());
		}
		System.out.println(miTablero.getStatus());
	}
}