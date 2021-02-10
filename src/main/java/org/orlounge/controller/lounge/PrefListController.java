package org.orlounge.controller.lounge;

import org.orlounge.auth.AuthCheck;
import org.orlounge.bean.InstrumentPrefListBean;
import org.orlounge.bean.PrefListBean;
import org.orlounge.common.AppConstants;
import org.orlounge.common.AppThreadLocal;
import org.orlounge.common.UserToken;
import org.orlounge.common.util.ControllerUtils;
import org.orlounge.common.util.MessageUtils;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;
import org.orlounge.controller.BaseController;
import org.orlounge.exception.ORException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Satyam Soni on 1/1/2016.
 */
@Controller
@AuthCheck

public class PrefListController extends BaseController {

    @RequestMapping("/pref-list.html")
    public ModelAndView prefs(@RequestParam(value = "page", required = false) Integer page, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/preflist-main");
            List<PrefListBean> scheduleBeans = getBusinessFactory().getPrefListBusiness().getAllPrefs();
            model.addObject("beans", scheduleBeans);
            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/instruments.html")
    public ModelAndView instruments(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, HttpSession httpSession) {
        try {

            ModelAndView model = new ModelAndView("jsp/instruments-main");
            PrefListBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getPrefListBusiness().getPrefById(id);
                model.addObject("pref", bean);
                model.addObject("beans", bean.getInstruments() == null ? new ArrayList<>() : bean.getInstruments());
            } else {
                return new ModelAndView("redirect:pref-list.html");

            }

            return model;

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping(value = "/saveinstrument.html")
    public ModelAndView saveIns(@ModelAttribute InstrumentPrefListBean bean, @RequestParam(value = "action") String action, MultipartFile file, HttpServletRequest request, HttpSession httpSession) {
        try {
            if (file != null && !file.isEmpty()) {
                if (!ControllerUtils.isValidFileSize(file, 2) || !ControllerUtils.isValidImageFile(file.getOriginalFilename())) {
                    MessageUtils.failure(request.getSession(), "Instrument Image is invalid.");
                } else {
                    getBusinessFactory().getPrefListBusiness().saveIns(bean, file);
                }
                ModelAndView modelAndView = null;
                if (bean.getPrefId() != null) {
                    modelAndView = view(bean.getPrefId(), action, request, httpSession);
                } else {
                    modelAndView = view(null, action, request, httpSession);
                }
                //return modelAndView;
            } else {
                getBusinessFactory().getPrefListBusiness().saveIns(bean, file);
                //return view(bean.getPrefId(), action, request, httpSession);
            }
            return new ModelAndView("redirect:pref-list.html");

        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    @RequestMapping(value = "/deleteinstrument.html")
    public ModelAndView deleteIns(@ModelAttribute InstrumentPrefListBean bean, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        try {
            getBusinessFactory().getPrefListBusiness().deleteIns(bean);
            return view(bean.getPrefId(), action, request, httpSession);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/savePreflist.html")
    public ModelAndView save(@ModelAttribute PrefListBean bean, HttpServletRequest request, HttpSession httpSession) {
        PrefListBean prefListBean = savePref(bean);
        ModelAndView model = new ModelAndView("redirect:viewpreflist.html?id="+prefListBean.getId()+"&action=update");
        List<PrefListBean> scheduleBeans = getBusinessFactory().getPrefListBusiness().getAllPrefs();
        model.addObject("beans", scheduleBeans);
        return model;
    }

    @RequestMapping(value = "/savePreflistAjax", produces = "application/json")
    public @ResponseBody
    PrefListBean saveAjax(@RequestBody() PrefListBean bean, HttpServletRequest request, HttpSession httpSession) {
        savePref(bean);
        return bean;
    }

    private PrefListBean savePref(PrefListBean bean) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (ProcessData.isValid(bean.getId())) {
                PrefListBean old = getBusinessFactory().getPrefListBusiness().getPrefById(bean.getId());
                bean.setCreatedDate(old.getCreatedDate());
                bean.setCreatedBy(old.getCreatedBy());
                bean.setGroupId(old.getGroupId());
                bean.setIsActive(old.getIsActive());
            } else {
                bean.setGroupId(token.getCurrentGroupId());
                bean.setCreatedBy(token.getUserId());
                bean.setCreatedDate(new Date());
                bean.setIsActive(1);
            }
            getBusinessFactory().getPrefListBusiness().savePrefList(bean);
            return bean;
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
    }

    @RequestMapping(value = "/saveBatchInstrument", produces = "application/json")
    public ModelAndView view(@RequestParam(value = "prefId", required = true) Integer prefId, @RequestParam(value = "action") String action, MultipartFile file, HttpServletRequest request, HttpSession httpSession) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            if (file != null && !file.isEmpty()) {

                if (!ControllerUtils.isValidExtension(file, ".csv")) {

                }

                CSVReader reader = new CSVReader();
                File f = new File(System.getProperty("java.io.tmpdir") + "\\uploads\\" + token.getUserCode() + "\\" + StringUtil.getUUID().replaceAll("-", "") + ".csv");
                f.createNewFile();
                InputStream in = null;
                FileOutputStream str = null;
                try {
                    in = file.getInputStream();
                    str = new FileOutputStream(f);
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        str.write(i);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (str != null) {
                        str.close();
                    }
                }

                List<InstrumentCSVData> data = reader.parse(f);
                f.deleteOnExit();
                for (InstrumentCSVData rec : data) {
                    try {
                        InstrumentPrefListBean bean = new InstrumentPrefListBean();
                        bean.setPrefId(prefId);
                        bean.setName(rec.getName());
                        bean.setCatalog(rec.getCatalog());
                        bean.setBin(rec.getBin());
                        bean.setQuantity(rec.getQuantity());
                        getBusinessFactory().getPrefListBusiness().saveIns(bean, null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }

            return view(prefId, action, request, httpSession);

        } catch (Exception ex) {
            MessageUtils.failure(request.getSession(), "Invalid File.Please refer to the template file by Downloading Template CSV");
            return view(prefId, action, request, httpSession);
        }

    }

    @RequestMapping(value = "/getSampleInstrument")
    public void sample(HttpServletRequest request, HttpServletResponse response, HttpServletResponse httpSession) {
        try {
            UserToken token = AppThreadLocal.getTokenLocal();
            File f = getBusinessFactory().getPrefListBusiness().downloadFile("preference-list/sample-instrument.csv");
            ControllerUtils.downloadFile(response, f, "sample-instrument-batch.csv", "csv");
            f.deleteOnExit();

        }catch (Exception ex){
            throw new ORException(ex, 0);
        }

    }

    @RequestMapping("/viewpreflist.html")
    public ModelAndView view(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "action") String action, HttpServletRequest request, HttpSession httpSession) {
        ModelAndView model = new ModelAndView("jsp/view-preflist");
        try {
            PrefListBean bean = null;
            if (ProcessData.isValid(id)) {
                bean = getBusinessFactory().getPrefListBusiness().getPrefById(id);
            } else {
                bean = new PrefListBean();
            }
            model.addObject("bean", bean);
            model.addObject("edit", true);
            model.addObject(AppConstants.ACTION, action);
        } catch (Exception ex) {
            throw new ORException(ex, 0);
        }
        return model;
    }

    @RequestMapping(value = "/new_delete_pref_list.html", method = RequestMethod.GET)
    public ModelAndView newDeletePrefList(@RequestParam("id") String id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:pref-list.html");
        try {
            boolean returnBln = getBusinessFactory().getPrefListBusiness().deletePreflist(id);
            if (returnBln) {
                MessageUtils.success(request.getSession(), "Removed Successfully.");
            } else {
                MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
            }

        } catch (Exception e) {
            MessageUtils.failure(request.getSession(), AppConstants.GLOBAL_FAILURE_MSG);
        }
        return model;
    }

    public class InstrumentCSVData {

        private String name;
        private String quantity;
        private String bin;
        private String catalog;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getBin() {
            return bin;
        }

        public void setBin(String bin) {
            this.bin = bin;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }
    }

    public class CSVReader {

        public List<InstrumentCSVData> parse(File file) throws IOException {

            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {

                br = new BufferedReader(new FileReader(file));
                List<InstrumentCSVData> data = new ArrayList<>();
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] eachLine = line.split(cvsSplitBy);
                    if (eachLine[0].equalsIgnoreCase("name")) {
                        continue;
                    }

                    if (eachLine.length == 4) {
                        InstrumentCSVData m = new InstrumentCSVData();
                        m.setName(eachLine[0]);
                        m.setQuantity(eachLine[1]);
                        m.setBin(eachLine[2]);
                        m.setCatalog(eachLine[3]);
                        data.add(m);
                    }
                }

                return data;

            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

}
