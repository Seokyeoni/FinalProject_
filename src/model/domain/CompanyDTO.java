package model.domain;

public class CompanyDTO {
	private String name;
	private String exchange;
	private String sector;
	private String industry;
	private String avgRtn;
	private String avgVol;
	
	public CompanyDTO(String name, String exchange, String sector, String industry, String avgRtn, String avgVol) {
		super();
		this.name = name;
		this.exchange = exchange;
		this.sector = sector;
		this.industry = industry;
		this.avgRtn = avgRtn;
		this.avgVol = avgVol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAvgRtn() {
		return avgRtn;
	}

	public void setAvgRtn(String avgRtn) {
		this.avgRtn = avgRtn;
	}

	public String getAvgVol() {
		return avgVol;
	}

	public void setAvgVol(String avgVol) {
		this.avgVol = avgVol;
	}

}
