import org.opensky.api.OpenSkyApi;
import org.opensky.model.OpenSkyStates;

import java.io.IOException;
import java.util.Collection;


public class OpenSky
{
  public void getData()
  {
    OpenSkyApi openSkyApi = new OpenSkyApi("adiv2", "openskytest110");
    try {
      OpenSkyStates os = openSkyApi.getStates(0, null);
      Collection collection = os.getStates();
      for (Object o:collection)
      {
        System.out.println(o.toString());
      }
      System.out.println(collection.size());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args)
  {
    OpenSky openSky = new OpenSky();
    openSky.getData();

  }

}
