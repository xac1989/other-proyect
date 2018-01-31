package com.cencosud.middleware.coupon.client;

public class CencoCoupons {
	
		private String codCampania;
		private String tipoDoc;
		private String numDoc;
		private String apePat;
		private String apeMat;
		private String fechaIni;
		private String fechaFin;
		private String horaIni;
		private String horaFin;
		private String cantCuponesRed;
		private String codBarraOffline;
		private String codOnline;
		private String legal;
		private String descuento;
		private String mensaje;
		private String codImagen;
		private String ambito;
		private String flagCanal;
		private String fechaRegistro;
		private String email;
		private String nombres;
		private String visualizacionAnticipada;
		private String r_n_n;
				
		public CencoCoupons() {
			super();
		}
	
		public CencoCoupons(String codCampania,String tipoDoc, String numDoc, String apePat, String apeMat, String fechaIni,
				String fechaFin, String horaIni, String horaFin, String cantCuponesRed, String codBarraOffline,
				String codOnline, String legal, String descuento, String mensaje, String codImagen, String ambito,
				String flagCanal, String fechaRegistro, String email, String nombres, String visualizacionAnticipada,
				String r_n_n) {
			super();
			this.codCampania = codCampania;
			this.tipoDoc = tipoDoc;
			this.numDoc = numDoc;
			this.apePat = apePat;
			this.apeMat = apeMat;
			this.fechaIni = fechaIni;
			this.fechaFin = fechaFin;
			this.horaIni = horaIni;
			this.horaFin = horaFin;
			this.cantCuponesRed = cantCuponesRed;
			this.codBarraOffline = codBarraOffline;
			this.codOnline = codOnline;
			this.legal = legal;
			this.descuento = descuento;
			this.mensaje = mensaje;
			this.codImagen = codImagen;
			this.ambito = ambito;
			this.flagCanal = flagCanal;
			this.fechaRegistro = fechaRegistro;
			this.email = email;
			this.nombres = nombres;
			this.visualizacionAnticipada = visualizacionAnticipada;
			this.r_n_n = r_n_n;
		}
		
		public String getNombres() {
			return nombres;
		}

		public void setNombres(String nombres) {
			this.nombres = nombres;
		}

		public String getCodCampania() {
			return codCampania;
		}
		public void setCodCampania(String codCampania) {
			this.codCampania = codCampania;
		}
		public String getNumDoc() {
			return numDoc;
		}
		public void setNumDoc(String numDoc) {
			this.numDoc = numDoc;
		}
		public String getApePat() {
			return apePat;
		}
		public void setApePat(String apePat) {
			this.apePat = apePat;
		}
		public String getApeMat() {
			return apeMat;
		}
		public void setApeMat(String apeMat) {
			this.apeMat = apeMat;
		}
		public String getFechaIni() {
			return fechaIni;
		}
		public void setFechaIni(String fechaIni) {
			this.fechaIni = fechaIni;
		}
		public String getFechaFin() {
			return fechaFin;
		}
		public void setFechaFin(String fechaFin) {
			this.fechaFin = fechaFin;
		}
		public String getHoraIni() {
			return horaIni;
		}
		public void setHoraIni(String horaIni) {
			this.horaIni = horaIni;
		}
		public String getHoraFin() {
			return horaFin;
		}
		public void setHoraFin(String horaFin) {
			this.horaFin = horaFin;
		}
		public String getCantCuponesRed() {
			return cantCuponesRed;
		}
		public void setCantCuponesRed(String cantCuponesRed) {
			this.cantCuponesRed = cantCuponesRed;
		}
		public String getCodBarraOffline() {
			return codBarraOffline;
		}
		public void setCodBarraOffline(String codBarraOffline) {
			this.codBarraOffline = codBarraOffline;
		}
		public String getCodOnline() {
			return codOnline;
		}
		public void setCodOnline(String codOnline) {
			this.codOnline = codOnline;
		}
		public String getLegal() {
			return legal;
		}
		public void setLegal(String legal) {
			this.legal = legal;
		}
		public String getDescuento() {
			return descuento;
		}
		public void setDescuento(String descuento) {
			this.descuento = descuento;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		public String getCodImagen() {
			return codImagen;
		}
		public void setCodImagen(String codImagen) {
			this.codImagen = codImagen;
		}
		public String getAmbito() {
			return ambito;
		}
		public void setAmbito(String ambito) {
			this.ambito = ambito;
		}
		public String getFlagCanal() {
			return flagCanal;
		}
		public void setFlagCanal(String flagCanal) {
			this.flagCanal = flagCanal;
		}
		public String getFechaRegistro() {
			return fechaRegistro;
		}
		public void setFechaRegistro(String fechaRegistro) {
			this.fechaRegistro = fechaRegistro;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getVisualizacionAnticipada() {
			return visualizacionAnticipada;
		}

		public void setVisualizacionAnticipada(String visualizacion_anticipada) {
			this.visualizacionAnticipada = visualizacion_anticipada;
		}
				
		public String getR_n_n() {
			return r_n_n;
		}

		public void setR_n_n(String r_n_n) {
			this.r_n_n = r_n_n;
		}

		public String getTipoDoc() {
			return tipoDoc;
		}

		public void setTipoDoc(String tipoDoc) {
			this.tipoDoc = tipoDoc;
		}





		public static class Token{
			private String access_token;
			private String token_type;
			
			public Token() {
				super();
			}
	
			public Token(String access_token, String token_type) {
				super();
				this.access_token = access_token;
				this.token_type = token_type;
			}
			
			public String getAccess_token() {
				return access_token;
			}
			public void setAccess_token(String access_token) {
				this.access_token = access_token;
			}
			public String getToken_type() {
				return token_type;
			}
			public void setToken_type(String token_type) {
				this.token_type = token_type;
			}
			
			
		}

}