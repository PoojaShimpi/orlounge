package org.orlounge.controller.user;

import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GeoLocationController extends BaseController {


    private static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist;
    }

    @RequestMapping("/checkLocation")
    public @ResponseBody
    boolean save(
            @RequestParam("lat") String lat,
            @RequestParam("lon") String lon,
            HttpServletRequest request,
            HttpSession session
    ) {

        try {
            Double lat1 = Double.parseDouble(lat);
            Double lng1 = Double.parseDouble(lon);

            UserToken token = AppThreadLocal.getTokenLocal();
            if (token.isHavingAccessRestriction()) {
                boolean isOkay = false;
                for (UserToken.Restriction r : token.getRestrictionInfo().getRestrictionsForDevice(UserToken.DeviceType.ALL)) {
                    for (UserToken.Coord c : r.getCoordinates()) {
                        float dist = (float) distFrom(lat1, lng1, c.getLat(), c.getLon());
                        if (dist <= r.getRadius()) {
                            isOkay = true;
                            return isOkay;
                        }
                    }

                }
                return isOkay;
            } else {
                return true;
            }

        } catch (Exception ex) {
            return false;
        }
    }
}
