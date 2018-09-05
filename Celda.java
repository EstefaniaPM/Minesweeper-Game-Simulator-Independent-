public class Celda
{
	private int contenido;
	private boolean destapada, marcada;

	public int getContenido()
	{
		return this.contenido;
	}
	public boolean getDestapada()
	{
		return this.destapada;
	}
	public boolean getMarcada()
	{
		return this.marcada;
	}
	public void setMarcada(boolean m)
	{
		this.marcada=m;
	}
	public void aumentaContenido()
	{
		if(this.contenido!=-1)
			this.contenido++;
	}
	public void destapar()
	{
		this.destapada=true;
	}
	public void ponerBomba()
	{
		this.contenido=-1;
	}
	public boolean esBomba()
	{
		if(this.contenido==-1)
			return true;
		return false;
	}
	public String toString()
	{
		if(this.destapada)
			if(this.contenido==-1)
				return "X";
			else
				return ""+this.contenido;
		if(this.marcada==true)
			return "*";
		return "-";
	}
}