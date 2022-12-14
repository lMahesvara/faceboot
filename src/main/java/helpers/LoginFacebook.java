package helpers;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import entidades.Usuario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginFacebook {

    private String domain = "https://localhost/";
    private String appId = "5760510044013251";
    private Usuario usuario;

    private String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain + "&scope=user_about_me,"
            + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_activities,user_birthday,user_education_history,"
            + "user_events,user_photos,user_games_activity,user_groups,user_hometown,user_interests,user_likes,user_location,user_photos,user_relationship_details,"
            + "user_relationships,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
            + "manage_notifications,manage_pages,publish_actions,read_insights,read_mailbox,read_page_mailboxes,read_stream,rsvp_event";

    public Usuario authUser() {

        System.setProperty("webdirver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
        String accessToken;
        try {
            while (true) {

                if (!driver.getCurrentUrl().contains("facebook.com")) {
                    String url = driver.getCurrentUrl();
                    accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");

                    String arregloToken[];
                    arregloToken = accessToken.split("&");
                    accessToken = arregloToken[0];

                    driver.quit();

                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    User user = fbClient.fetchObject("me", User.class, Parameter.with("fields", "id, name,email"));
                    
//                    usuario = new Usuario(user.getName());
//                    Calendar cal = Calendar.getInstance();
//                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
//                    cal.setTime(sdf.parse(user.getBirthday()));
//                    
//                    usuario.setCorreo(user.getEmail());
//                    usuario.setToken(user.getId());
//                    usuario.setFechaNacimiento(cal);
                    
                    usuario = new Usuario(user.getId(), user.getEmail(), user.getName());
                    System.out.println(user.getName());
                    System.out.println(user.getId());
                    System.out.println(user.getEmail());

                }
            }
        } catch (Exception e) {
        }
        return usuario;
    }

}
  
    
