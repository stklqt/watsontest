package wasdev.sample.servlet;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v2.util.Language;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet(“/SimpleServlet”)
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print(translateToSpanish("This is a test"));
    }

    private String translateToSpanish(String text){

        LanguageTranslator service = new LanguageTranslator();
        service.setUsernameAndPassword("uname", "pwd"); //FIXME anpassen
        TranslationResult translationResult = service.translate(text, Language.ENGLISH, Language.GERMAN).execute();
        return translationResult.getTranslations().get(0).toString();
    }

}
