package Service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import DAO.ComodoComponenteDAO;
import DAO.ComodoDAO;
import DAO.ComponenteDAO;
import android.content.Context;
import android.os.AsyncTask;
import bean.Comodo;
import bean.ComodoComponente;
import bean.Componente;

public class ConexaoWS extends AsyncTask<String, Void, String> {
	String resultado = null;


	String result;
	
	public AsyncResponse delegate;
	public Context cnt;
	public int idusuario;
	ComodoDAO comodoDAO;
	ComponenteDAO componenteDAO;
	ComodoComponenteDAO comodoComponenteDAO;


	@Override
	protected String doInBackground(String... params) {
		final String NAMESPACE = params[0];
		final String METHOD_NAME = params[1];
		final String URL = "http://192.168.1.101:8080/ElektraWebService/services/"
				+ params[2];
		final String SOAP_ACTION = params[3];
		try {
			SoapObject so = new SoapObject(NAMESPACE, METHOD_NAME);

			for (int i = 4; i < params.length; i++) {
				PropertyInfo property = new PropertyInfo();
				property.setName(params[i]);
				property.setValue(params[++i]);
				so.addProperty(property);
			}

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = so;

			HttpTransportSE http = new HttpTransportSE(URL);

			try {
				http.call(SOAP_ACTION, envelope);

				SoapObject s = (SoapObject) envelope.bodyIn;
				String name = s.getName().toString();

				switch (name) {
				case "verificaUsuarioResponse":
					resultado = s.getProperty(0).toString();
					break;

				case "recuperaUsuarioResponse":
					ComodoComponente cc = new ComodoComponente();
					Comodo c = new Comodo();
					Componente cp = new Componente();
					int count = s.getPropertyCount();
					resultado = "Falhou";
				
					
					 comodoDAO = ComodoDAO.getInstance(cnt);
					 comodoDAO.excluir();
					
					for (int i = 0; i < count; i++) {

						SoapObject resultComodoComponente = (SoapObject) s
								.getProperty(i);

						SoapObject result = (SoapObject) resultComodoComponente
								.getProperty("comodoComponente");
						SoapObject resultComodo = (SoapObject) result
								.getProperty("comodo");
						SoapObject resultComponente = (SoapObject) result
								.getProperty("componentes");

						c.setIdComodo(Integer.parseInt(resultComodo
								.getProperty("idComodo").toString()));
						c.setDescricao(resultComodo.getProperty("descricao")
								.toString());

						/*
						 * aqui vc já pode salvar o c no sqlite fazer o método
						 * retornar ok ou falha. e depois colocar isso no
						 * resultado. exemplo: resultado = Falha ao inserir
						 * comodo break;
						 */

						cp.setIdComponente(Integer.parseInt(resultComponente
								.getProperty("idComponente").toString()));
						cp.setDescricao(resultComponente.getProperty(
								"descricao").toString());

						/*
						 * aqui vc já pode salvar o c no sqlite fazer o método
						 * retornar ok ou falha. e depois colocar isso no
						 * resultado. exemplo: resultado = Falha ao inserir
						 * comodo break;
						 */

						cc.setIdComodoComponente(Integer.parseInt(result
								.getProperty("idComodoComponente").toString()));
						cc.setComodo(c);
						cc.setComponentes(cp);
						cc.setDataFinal((java.sql.Date) result
								.getProperty("dataFinal") == null ? null
								: (java.sql.Date) result
										.getProperty("dataFinal"));
						cc.setDataInicial((java.sql.Date) result
								.getProperty("dataInicial") == null ? null
								: (java.sql.Date) result
										.getProperty("dataInicial"));
						cc.setPorta(Integer.parseInt(result
								.getProperty("porta").toString()));
						cc.setSensor(Integer.parseInt(result.getProperty(
								"sensor").toString()));
						cc.setStatus(Integer.parseInt(result.getProperty(
								"status").toString()));
						cc.setTemporizador(Integer.parseInt(result.getProperty(
								"temporizador").toString()));

						
						if(cc != null && cp != null && c != null)
						 {
							 comodoComponenteDAO = ComodoComponenteDAO.getInstance(cnt);
							 if(comodoComponenteDAO.buscar(cc.getIdComodoComponente()).equals("Encontro"))
							 {
								 comodoComponenteDAO.alterar(cc);
							 }
							 else
							 {
								 comodoComponenteDAO.incluir(cc); 
							 }
								 
							 
							 
							 componenteDAO = ComponenteDAO.getInstance(cnt);
							 if(componenteDAO.buscar(cp.getIdComponente()).equals("Encontro"))
							 {
								 componenteDAO.alterar(cp);
							 }
							 else
							 {	 
								 componenteDAO.incluir(cp);
						 	 }
							 
							 comodoDAO = ComodoDAO.getInstance(cnt);
							 if(comodoDAO.buscar(c.getIdComodo()).equals("Encontro"))
							 {
								 comodoDAO.alterar(c);
							 }
							 else
							 {

								 comodoDAO.incluir(c); 
							 }
							
							
							 resultado = "ok";
						 }
						/*
						 * aqui vc já pode salvar o cc no sqlite fazer o método
						 * retornar ok ou falha. e depois colocar isso no
						 * resultado. exemplo: resultado = Falha ao inserir
						 * comodo componente break;
						 */

					}
					break;

				case "ligarDesligarComponentesResponse":
					ComodoComponente ccUpdate = new ComodoComponente();
					ccUpdate.setStatus(Integer.parseInt(s.getProperty(0)
							.toString()));
					resultado = s.getProperty(0).toString();
					/*
					 * aqui vc já pode salvar o cc no sqlite fazer o método
					 * retornar ok ou falha. e depois colocar isso no resultado.
					 * exemplo: resultado = Falha ao atualizar comodo ou
					 * retornar que o componente já está aceso ou apagado se
					 * virem um pouco :) break;
					 */
					break;

				default:
					break;
				}

			} catch (Exception e) {
				// TODO: handle exception
				
				resultado = "Falha";
				
				e.printStackTrace();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultado;

	}

	protected void onPostExecute(String result) {

		if (delegate != null) {
			delegate.processoFinalizado(result);	
		}
		
	}
}
