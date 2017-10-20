package wasdev.sample.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import static com.ibm.watson.developer_cloud.language_translator.v2.util.Language.ENGLISH;
import static com.ibm.watson.developer_cloud.language_translator.v2.util.Language.GERMAN;


/**
 * Servlet implementation class MySimpleTestServlet
 */
@WebServlet("/MySimpleTestServlet")
public class MySimpleTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print(translateToGerman("This is a really great test"));
        throw new RuntimeException();
    }

    private String translateToGerman(String text) {

        LanguageTranslator service = new LanguageTranslator();
        TranslateOptions translateOptions = null;
        translateOptions.newBuilder().addText(text).source(ENGLISH).target(GERMAN).build();

        service.setUsernameAndPassword("77b8b56c-b9ca-4275-98e7-1d26a490c341", "B4msnYxH6IDW");  //FIXME
        TranslationResult translationResult = service.translate(translateOptions).execute();
        return translationResult.getTranslations().get(0).toString();
    }

}
