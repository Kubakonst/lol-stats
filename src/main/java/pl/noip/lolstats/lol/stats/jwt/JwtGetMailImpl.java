package pl.noip.lolstats.lol.stats.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtGetMailImpl implements JwtGetMail {


@Override

public String getmail(String token)
{
    String result = "";

    String[] parts = token.split("[.]");
    try
    {
        int index = 0;
        for(String part: parts)
        {
            if (index >= 2)
                break;

            index++;
            byte[] partAsBytes = part.getBytes("UTF-8");
            String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), "UTF-8");

            result += decodedPart;

        }
    }
    catch(Exception e)
    {
        throw new RuntimeException("Couldnt decode jwt", e);
    }

    return result;
}
}



