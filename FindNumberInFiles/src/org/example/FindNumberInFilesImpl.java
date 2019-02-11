package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

@Service
@Transactional
public class FindNumberInFilesImpl implements FindNumberInFiles {
	static String code = "01.Result.NotFound";                                 // У этих полей должен быть тип "package" чтобы findNumber мог сформировать fileNames
	static List<String> fileNames = new ArrayList<String>();
	static String error = "";
	private String numberSearch;
	private List<String> filesForSearch = new ArrayList<String>();

	// Логирование (log4j)
	static Logger log = Logger.getLogger("example/FindNumberInFilesImpl");

	public Result findNumber(Integer number) throws Exception {
		numberSearch = Integer.toString(number);        // у нас получаемые из файла значения будут типа String и для того чтобы не преобразовывать каждое из них к типу Integer, мы преобразуем тип Integer параметра этого метода к типу String

		filesForSearch.add("G:/1");
		filesForSearch.add("G:/2");
		filesForSearch.add("G:/3");
		filesForSearch.add("G:/4");
		filesForSearch.add("G:/5");
		filesForSearch.add("G:/6");
		filesForSearch.add("G:/7");
		filesForSearch.add("G:/8");
		filesForSearch.add("G:/9");
		filesForSearch.add("G:/10");
		filesForSearch.add("G:/11");
		filesForSearch.add("G:/12");
		filesForSearch.add("G:/13");
		filesForSearch.add("G:/14");
		filesForSearch.add("G:/15");
		filesForSearch.add("G:/16");
		filesForSearch.add("G:/17");
		filesForSearch.add("G:/18");
		filesForSearch.add("G:/19");
		filesForSearch.add("G:/20");

		for (String path : filesForSearch) {
			findNumberInOneFile(path);
		}

		Result result = new Result(code, numberSearch, fileNames, error);

		// HIBERNATE
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		session.save(result);
		session.getTransaction().commit();

		// по завершении работы с одним числом откатываем измения переменных до default-значений
		code = "01.Result.NotFound";
		fileNames.clear();
		error = "";
		filesForSearch.clear();

		return result;
	}

	public boolean findNumberInOneFile(String path) {
		try {

			String remainer = "";              // остаток (мы получаем не целиком весь гигантский файл, а частями, при этом может так получиться, что одно число будет разбито на два потока (начала числа прийдёт в конце одного потока, а конец числа
									              // в начале следующего потока), поэтому в каждом потоке последнее после запятой значение мы извелкаем и присоединяем к получаемой строке следующего потока. Самое поледнее число файла мы просто
									              // отдельно сравним с параметром.

			BufferedReader fInp = new BufferedReader(new FileReader(path));
			while (fInp.ready()) {
				char[] charBuffer = new char[10000];
				fInp.read(charBuffer);         // читаем в буфер символов (ограниченного размера) и записываем в переменную charBufferLen количество реально прочитанных символов, т.к. в последнем потоке символов будет меньше чем размер charBuffer

				String input = remainer;                           // прибавляем к строке остаток предыдущего потока

				input = input + String.valueOf(charBuffer).replaceAll("[^0-9-,]", "");

				String[] numberBuffer = input.split(",");          // разбиваем полученную строку на слова (числа) относительно запятых

				int numberBufferLen = numberBuffer.length;

				// вычисляем остаток и узнаем сколько слов мы будем проверять на соответствие нашему параметру
				if (charBuffer[charBuffer.length - 1] == ',')
					remainer = "";
				else {
					remainer = numberBuffer[numberBuffer.length - 1];
					numberBufferLen = numberBuffer.length - 1;
				}

				for (int i = 0; i < numberBufferLen; i++) {

					if (numberBuffer[i].equals(numberSearch)) {
						if (code.equals("01.Result.NotFound"))
							code = "00.Result.OK";
						fileNames.add(new File(path).getName());
						fInp.close();
						return true;
					}

				}
			}
			fInp.close();

			if (remainer.equals(numberSearch)) {
				if (code.equals("01.Result.NotFound")) {
					code = "00.Result.OK";
				}
				fileNames.add(new File(path).getName());
				return true;
			} else {
				return true;
			}
		} catch (Exception e) {
			code = "02.Result.Error";
			if (e.getMessage() != null)
				error = error + "\n" + "Возникла ошибка при работе с файлом " + path + ": " + e.getMessage();
			else
				error = error + "\n" + "Возникла ошибка при работе с файлом " + path;
		}
		return false;     // будет выполнено в случае возникновения exception
	}
}