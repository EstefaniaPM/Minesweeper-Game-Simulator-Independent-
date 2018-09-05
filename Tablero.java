import java.util.Random;
public class Tablero
{
	private Random rd=new Random();
	private String status;
	private boolean juegoTerminado;
	private int tamanio, bombas, tiros;
	private Celda[][] matriz;

	public Tablero(int t)
	{
		this.tamanio=t;
		matriz=new Celda[this.tamanio][this.tamanio];
		for(int i=0; i<this.tamanio; i++)
			for(int j=0; j<this.tamanio; j++)
				matriz[i][j]=new Celda();
		this.ponerBombas();
	}
	public String getStatus()
	{
		return this.status;
	}
	public boolean getJuegoTerminado()
	{
		return this.juegoTerminado;
	}
	public int getBombas()
	{
		return this.bombas;
	}
	public void ponerBombas()
	{
		int a, b;
		int contador=0;
		double bomba=this.tamanio*this.tamanio*0.17;
		this.bombas=(int)bomba;
		this.tiros=(this.tamanio*this.tamanio)-this.bombas;
		while(contador<this.bombas)
		{
			a=rd.nextInt(this.tamanio);
			b=rd.nextInt(this.tamanio);
			if(matriz[a][b].esBomba()==false)
			{
				matriz[a][b].ponerBomba();
				this.ponerNumeros(a, b);
				contador++;
			}
		}
	}
	public void ponerNumeros(int a, int b)
	{
		if(a>0)
		{
			matriz[a-1][b].aumentaContenido();
			if(b>0)
				matriz[a-1][b-1].aumentaContenido();
			if(b<this.tamanio-1)
				matriz[a-1][b+1].aumentaContenido();
		}
		if(a<this.tamanio-1)
		{
			matriz[a+1][b].aumentaContenido();
			if(b>0)
				matriz[a+1][b-1].aumentaContenido();
			if(b<this.tamanio-1)
				matriz[a+1][b+1].aumentaContenido();
		}
		if(b>0)
			matriz[a][b-1].aumentaContenido();
		if(b<this.tamanio-1)
			matriz[a][b+1].aumentaContenido();
	}
	public String toString()
	{
		String str="   ";
		for(int i=0; i<this.tamanio; i++)
		{
			if(i>=10)
				str+=" "+i;
			else
				str+="  "+i;
		}
		str+="\n\n";
		for(int i=0; i<this.tamanio; i++)
		{
			if(i>=10)
				str+=i+"   ";
			else
				str+=i+"    ";
			for(int j=0; j<this.tamanio; j++)
				str+=matriz[i][j].toString()+"  ";
			str+="\n";
		}
		return str;
	}
	public boolean validaTiro(int a, int b)
	{
		if(matriz[a][b].getDestapada()==true)
			return false;
		return true;
	}
	public void tiro(int a, int b)
	{
		if(!matriz[a][b].esBomba())
		{
			if(this.tiros==1)
			{
				this.juegoTerminado=true;
				this.status="Ganaste";
				for(int i=0; i<this.tamanio; i++)
					for(int j=0; j<this.tamanio; j++)
						matriz[i][j].setMarcada(true);
			}
			destaparCeldas(a, b);
		}
		else
		{
			for(int i=0; i<this.tamanio; i++)
				for(int j=0; j<this.tamanio; j++)
					if(matriz[i][j].esBomba()==true)
						matriz[i][j].destapar();
			this.juegoTerminado=true;
			this.status="Perdiste";
		}
	}
	public void destaparCeldas(int a, int b)
	{
		if(!matriz[a][b].getDestapada())
		{
			matriz[a][b].destapar();
			this.tiros--;

			if(matriz[a][b].getContenido() == 0)
			{
				if(a>0 && !matriz[a-1][b].esBomba())
					destaparCeldas(a-1, b);
				
				if(a>0 && b>0 && !matriz[a-1][b-1].esBomba())
					destaparCeldas(a-1, b-1);
				
				if(a>0 && b<this.tamanio-1 && !matriz[a-1][b+1].esBomba())
					destaparCeldas(a-1, b+1);
				
				if(a<this.tamanio-1 && !matriz[a+1][b].esBomba())
					destaparCeldas(a+1, b);
				
				if(a<this.tamanio-1 && b>0 && !matriz[a+1][b-1].esBomba())
					destaparCeldas(a+1, b-1);
				
				if(a<this.tamanio-1 && b<this.tamanio-1 && !matriz[a+1][b+1].esBomba())
					destaparCeldas(a+1, b+1);
				
				if(b>0 && !matriz[a][b-1].esBomba())
					destaparCeldas(a, b-1);
				
				if(b<this.tamanio-1 && !matriz[a][b+1].esBomba())
					destaparCeldas(a, b+1);
			}
		}
	}
	public void marcarCelda(int a, int b)
	{
		if(matriz[a][b].getMarcada()==false)
			matriz[a][b].setMarcada(true);
		else
			matriz[a][b].setMarcada(false);
	}

}

/*

if(matriz[a][b].getContenido() == 0)
		{
			if(a>0)
			{
				if(!matriz[a-1][b].esBomba())
				{
					matriz[a-1][b].destapar();
					destaparCeldas(a-1, b);
				}
				if(b>0 && !matriz[a-1][b-1].esBomba())
				{
					matriz[a-1][b-1].destapar();
					destaparCeldas(a-1, b-1);
				}
				if(b<this.tamanio-1 && !matriz[a-1][b+1].esBomba())
				{
					matriz[a-1][b+1].destapar();
					destaparCeldas(a-1, b+1);
				}
			}
			if(a<this.tamanio-1)
			{
				if(!matriz[a+1][b].esBomba())
				{
					matriz[a+1][b].destapar();
					destaparCeldas(a+1, b);
				}
				if(b>0 && !matriz[a+1][b-1].esBomba())
				{
					matriz[a+1][b-1].destapar();
					destaparCeldas(a+1, b-1);
				}
				if(b<this.tamanio-1 && !matriz[a+1][b+1].esBomba())
				{
					matriz[a+1][b+1].destapar();
					destaparCeldas(a+1, b+1);
				}
			}
			if(b>0 && !matriz[a][b-1].esBomba())
			{
				matriz[a][b-1].destapar();
				destaparCeldas(a, b-1);
			}
			if(b<this.tamanio-1 && !matriz[a][b+1].esBomba())
			{
				matriz[a][b+1].destapar();
				destaparCeldas(a, b+1);
			}
		}
		else
			return;

*/