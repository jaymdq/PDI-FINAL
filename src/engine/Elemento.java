package engine;

public class Elemento {

	private Integer y;
	private Integer xinicial;
	private Integer xfinal;
	
	public Elemento(Integer y, Integer xi, Integer xf){
		this.setY(y);
		this.setXinicial(xi);
		this.setXfinal(xf);
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getXinicial() {
		return xinicial;
	}

	public void setXinicial(Integer xinicial) {
		this.xinicial = xinicial;
	}

	public Integer getXfinal() {
		return xfinal;
	}

	public void setXfinal(Integer xfinal) {
		this.xfinal = xfinal;
	}
	
	
	
	
}
