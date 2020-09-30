package testTrustly;

import java.util.ArrayList;
import java.util.Map;

import javax.sql.DataSource;

import java.lang.String;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@SpringBootApplication

public class AnalystRepository {
	
	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	static AnalystRepository obj = new AnalystRepository();
	static String address = "https://github.com/mariazevedo88/artigo-solid-medium";
	static ArrayList<String> arrayList = new ArrayList<String>();
	static ArrayList<FileInfo> array_dados = new ArrayList<FileInfo>();
	static final int NO_SUBFOLDERS = -1;
	static final String KEYWORD_INFO_FILE = "file-info-divider";
	
	
	public static void main(String[] args ) throws Exception {	
		SpringApplication.run(AnalystRepository.class, args);
	    System.out.print("TESTANDO SPRING EM ANALISE.[");

	}
	
	  @RequestMapping("/analysisGit")
	  String hello(Map<String, Object> model) {
		  System.out.print("Starting repository count "+address+"\n");
			//getPath(address);
	      model.put("science", "Isso é um teste do krl, "+ "Iniciando...");
	      return "analysisHtml";
	  }

	
	public static String[] getLink(String contentPage, int indexKeyword) {
		String new_string;
		int ind_div = contentPage.indexOf("/div",indexKeyword);
		new_string = contentPage.substring(indexKeyword, ind_div);
		
		while (!ValidatePath.validatePath(new_string)) {
			contentPage = contentPage.substring(ind_div);
			int ind = contentPage.indexOf("rowheader");
			ind_div = contentPage.indexOf("/div",ind);
			new_string = contentPage.substring(ind, ind_div);
		}
		contentPage = contentPage.substring(ind_div);
		arrayList.add(contentPage);
		new_string = CreateLink.createLink(new_string);
		
		String[] dados_analisados = {contentPage,new_string};				
		return dados_analisados;
	}
	
	public static void getPath(String filePath) {
		//This function aims to make GET for the repository and check if there are sub-folders.
		try {
			String contentPage = RequestHttp.sendGet(filePath);  
			if (FindKey.findRowHeader(contentPage) == NO_SUBFOLDERS) {
				array_dados = CreateArrayResult.createArrayResult(array_dados, CalculatesData.calculatesData(address, contentPage, filePath));
				checkLevelUp();
			}else {
				nextLevel(contentPage,FindKey.findRowHeader(contentPage));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void checkLevelUp() {
		//
		if (arrayList.size() > 0) {
			if (FindKey.findRowHeader(arrayList.get(arrayList.size() -1 ) ) == -1) {
				arrayList.remove(arrayList.size() -1 );
				if (arrayList.size() != 0) {
					checkLevelUp();
				}else {
					System.out.print("\n\nFinal Result:\n\n");
					for (int ind = 0; ind < array_dados.size(); ind++) {
						System.out.print("\nFormat: "+array_dados.get(ind).format+"\nLines: "+array_dados.get(ind).lines+"\nBytes: "+array_dados.get(ind).bytes+"\n\n");
					}
					System.out.print("\n\nFINAL RUN\n\n");
				}
			}else {
				String htmlText = arrayList.get(arrayList.size()-1);
				int indexDiv = htmlText.indexOf("/div",FindKey.findRowHeader(arrayList.get(arrayList.size() -1 ) ) );
				address = htmlText.substring(FindKey.findRowHeader(arrayList.get(arrayList.size() -1 ) ), indexDiv);
				
				while (!ValidatePath.validatePath(address)) {
					htmlText = htmlText.substring(indexDiv);
					int indexRowHeader = htmlText.indexOf("rowheader");
					indexDiv = htmlText.indexOf("/div",indexRowHeader);
					address = htmlText.substring(indexRowHeader, indexDiv);
				}
				
				htmlText = htmlText.substring(indexDiv);
				arrayList.set(arrayList.size()-1, htmlText);
				address = CreateLink.createLink(address);
				getPath(address);
			}
		}else {
			System.out.print("\n\nFinal Result:\n\n");
			for (int ind = 0; ind < array_dados.size(); ind++) {
				System.out.print("\nFormat: "+array_dados.get(ind).format+"\nLines: "+array_dados.get(ind).lines+"\nBytes: "+array_dados.get(ind).bytes+"\n\n");
			}
			System.out.print("\n\nFINAL RUN\n\n");
		}
	}
	
	public static void nextLevel(String contentPage, int indexKeyWord) {
		String[] infoPath = getLink(contentPage,indexKeyWord);

		if (FindKey.findRowHeader(infoPath[0]) == NO_SUBFOLDERS) {
			try {
				String fileLastLevel = RequestHttp.sendGet(infoPath[1]);
				array_dados = CreateArrayResult.createArrayResult(array_dados, CalculatesData.calculatesData(address, fileLastLevel, infoPath[1]));
				checkLevelUp();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			getPath(infoPath[1]);
		}
	}	
	
	  @Bean
	  public DataSource dataSource() throws SQLException {
	    if (dbUrl == null || dbUrl.isEmpty()) {
	      return new HikariDataSource();
	    } else {
	      HikariConfig config = new HikariConfig();
	      config.setJdbcUrl(dbUrl);
	      return new HikariDataSource(config);
	    }
	  }
}

