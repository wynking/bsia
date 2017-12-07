package cn.com.pansky.otp5.association.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.pagehelper.StringUtil;

import cn.com.pansky.otp5.association.dao.IEnterpriseBasicDao;
import cn.com.pansky.otp5.association.dao.po.AnnualReport;
import cn.com.pansky.otp5.association.dao.po.AptitudeRelate;
import cn.com.pansky.otp5.association.dao.po.ContactInfo;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.association.dao.po.IndustryRelate;
import cn.com.pansky.otp5.association.dao.po.KnowledgeRight;
import cn.com.pansky.otp5.association.dao.po.Production;
import cn.com.pansky.otp5.association.dao.po.ProductionFieldRelate;
import cn.com.pansky.otp5.association.dao.po.Shareholder;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DateUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.SpringContextHolder;
import cn.com.pansky.otp5.common.SystemContext;

public class ExcelToDB {

    public static Logger logger = Logger.getLogger(ExcelToDB.class);
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    // 判断Excel的版本,获取Workbook
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
            wb = new XSSFWorkbook(file.getPath());
        }
        return wb;
    }

    // 判断文件是否是excel
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    // 由指定的Sheet导出至List
    private static Sheet getSheetFromExcel(File excelFile, int sheetNum) throws Exception {

        // SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        // 同时支持Excel 2003、2007
        // File excelFile = new File("D:/123.xls"); // 创建文件对象
        FileInputStream is = new FileInputStream(excelFile); // 文件流
        // checkExcelVaild(excelFile);
        // Workbook workbook = getWorkbok(is,excelFile);
        Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel2003/2007/2010都是可以处理的
        //int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
        
        Sheet sheet = workbook.getSheetAt(sheetNum); // 遍历第一个Sheet
        return sheet;
    }

    private static String convertCellToValue(Cell cell, int cellType) throws ParseException {
        String cellValue;
        switch (cellType) {
        case Cell.CELL_TYPE_STRING: // 文本
            cellValue = cell.getRichStringCellValue().getString();
            break;
        case Cell.CELL_TYPE_NUMERIC: // 数字、日期
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                cellValue = DateUtil.parseDateToStr(date, DateUtil.FORMAT_2);
            } else {
                cellValue = String.valueOf(cell.getNumericCellValue());
            }
            break;
        default:
            System.err.println("default=================>>>>数据类型不支持！！！===" + cellType);
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param sheet
     * @return
     * @throws Exception
     */
    private static List<BasePO> bulidDictionary(Sheet sheet) throws Exception {
        // Sheet sheet = getSheetFromExcel(excelFile);
        List<BasePO> dics = new ArrayList<BasePO>();
        int count = 0;
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            Dictionary dic = new Dictionary();
            int i = 0;
            for (Cell cell : row) {
                int cellType = cell.getCellType();
                String cellValue = convertCellToValue(cell, cellType);

                if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                    if (i == 0) {
                        dic.setId(cellValue);
                    } else if (i == 1) {
                        dic.setCode(cellValue);
                    } else if (i == 2) {
                        dic.setName(cellValue);
                    } else if (i == 3) {
                        dic.setType(cellValue);
                    } else if (i == 4) {
                        dic.setEnable(cellValue.substring(0, 1));
                    } else if (i == 5) {
                        dic.setpCode(cellValue);
                    } else if (i == 6) {
                        dic.setIsDicName(cellValue.substring(0, 1));
                    } else if (i == 7) {
                        dic.setLevel(Integer.valueOf(cellValue));
                    }

                }

                i++;

            }
            dics.add(dic);
        }
        return dics;
    }

    private static String getEIdByCode(String code, List<EnterpriseBasic> pos) {
        for (EnterpriseBasic po : pos) {
            if (code.equals(po.getCode())) {
                return po.getId();
            }
        }
        return "";
    }

    /**
     * @Description 组织企业基本信息
     * @param excelFile
     * @param dataType
     * @return
     * @throws Exception
     */
    private static List<BasePO> bulidBasic(Sheet sheet, HttpServletRequest request) throws Exception {
        // String format = request.getParameter("format");
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }

            EnterpriseBasic po = new EnterpriseBasic();
            int len = row.getLastCellNum();
            try {
                //int a = 1 / 0;
                for (int i = 0; i < len; i++) {
                    
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }
                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            po.setName(cellValue);
                        } else if (i == 2) {
                            po.setUrl(cellValue);
                        } else if (i == 3) {
                            if(cellValue.indexOf(".")!=-1){
                                po.setCode(cellValue.substring(0,cellValue.indexOf(".")));
                            }else{
                                po.setCode(cellValue);
                            }
                        } else if (i == 4) {
                            if ("否".equals(cellValue)) {
                                po.setIsPublic("0");
                            } else if ("是".equals(cellValue)) {
                                po.setIsPublic("1");
                            } else {
                                po.setIsPublic(cellValue);
                            }
                        } else if (i == 5) {
                            // po.setIsHigh(cellValue.substring(0, 1));
                            if ("否".equals(cellValue)) {
                                po.setIsHigh("0");
                            } else if ("是".equals(cellValue)) {
                                po.setIsHigh("1");
                            } else {
                                po.setIsHigh(cellValue);
                            }
                        } else if (i == 6) {
                            po.setPassHighDate(DateUtil.parseStrToDate(cellValue));
                        } else if (i == 7) {
                            if ("否".equals(cellValue)) {
                                po.setIsMember("0");
                            } else if ("是".equals(cellValue)) {
                                po.setIsMember("1");
                            } else {
                                po.setIsMember(cellValue);
                            }
                        } else if (i == 8) {
                            po.setFirstTime(DateUtil.parseStrToDate(cellValue));
                        } else if (i == 9) {
                            po.setHyLevel(
                                    DictionaryUtil.getCodeByNameType(request, cellValue, ConstantUtil.DIC_HY_LEVEL));
                        } else if (i == 10) {
                            po.setRegisteredCapital(Double.valueOf(cellValue));
                        } else if (i == 11) {
                            po.setEmployeeNum(cellValue);
                        } else if (i == 12) {
                            po.setAddress(DictionaryUtil.getRegionCodeByName(cellValue, request));
                        } else if (i == 13) {
                            po.setAddressDetail(cellValue);
                        } else if (i == 14) {
                            po.setPostalCode(cellValue);
                        } else if (i == 15) {
                            po.setRegisterAddress(DictionaryUtil.getRegionCodeByName(cellValue, request));
                        } else if (i == 16) {
                            po.setRegisterAddressDetail(cellValue);
                        } else if (i == 17) {
                            if ("删除".equals(cellValue)) {
                                po.setEnable("2");
                            } else if ("正常".equals(cellValue)) {
                                po.setEnable("1");
                            } else {
                                po.setEnable(cellValue);
                            }
                        } else if (i == 18) {
                            if (cellValue.contains(".")) {
                                po.setAvgNum(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            } else {
                                po.setAvgNum(Integer.valueOf(cellValue));
                            }

                        } else if (i == 19) {
                            if (cellValue.contains(".")) {
                                po.setYfNum(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            } else {
                                po.setYfNum(Integer.valueOf(cellValue));
                            }
                        } else if (i == 20) {
                            if (cellValue.contains(".")) {
                                po.setGlNum(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            } else {
                                po.setGlNum(Integer.valueOf(cellValue));
                            }
                        } else if (i == 21) {
                            if (cellValue.contains(".")) {
                                po.setZbNum(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            } else {
                                po.setZbNum(Integer.valueOf(cellValue));
                            }
                        } else if (i == 22) {
                            if (cellValue.contains(".")) {
                                po.setSsNum(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            } else {
                                po.setSsNum(Integer.valueOf(cellValue));
                            }
                        } else if (i == 23) {
                            if (cellValue.contains(".")) {
                                po.setBsNum(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            } else {
                                po.setBsNum(Integer.valueOf(cellValue));
                            }
                        } else if (i == 24) {
                            po.setCreationUser(cellValue);
                        } else if (i == 25) {
                            po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 26) {
                            po.setLastModifyUser(cellValue);
                        } else if (i == 27) {
                            po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 28) {
                            po.setInfoSources(DictionaryUtil.getCodeByNameType(request, cellValue,
                                    ConstantUtil.DIC_INFO_SOURCES));
                        } else if (i == 29) {
                            po.setRemark(cellValue);
                        } else if (i == 30) {
                            po.setLongitude(cellValue);
                        } else if (i == 31) {
                            po.setDimension(cellValue);
                        }else if (i == 32) {
                            po.setOwner(cellValue);
                        }else if (i == 33) {
                            po.setFzrName(cellValue);;
                        }else if (i == 34) {
                            po.setFzrJob(cellValue);
                        }else if (i == 35) {
                            po.setFzrPhone(cellValue);
                        }else if (i == 36) {
                            po.setFzrTel(cellValue);
                        }else if (i == 37) {
                            po.setFzrEmail(cellValue);
                        }else if (i == 38) {
                            po.setLxrName(cellValue);
                        }else if (i == 39) {
                            po.setLxrJob(cellValue);
                        }else if (i == 40) {
                            po.setLxrPhone(cellValue);
                        }else if (i == 41) {
                            po.setLxrTel(cellValue);
                        }else if (i == 42) {
                            po.setLxrEmail(cellValue);
                        }
                    }
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>基础信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    private static List<BasePO> bulidAptitude(Sheet sheet, HttpServletRequest request) throws Exception {
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        // System.out.println("basics======bulidAptitude========================>>>>" + basics.size());
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            AptitudeRelate po = new AptitudeRelate();
            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                            // System.out.println("id=========bulidAptitude================>>>" + cellValue);
                        }
                    }
                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setCode(DictionaryUtil.getCodeByNameType(request, cellValue,
                                    ConstantUtil.DIC_INFO_APTITUDE));
                        } else if (i == 3) {
                            po.setPassTime(DateUtil.parseStrToDate(cellValue));
                        }
                    }
                }

                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>资质信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    private static List<BasePO> bulidTech(Sheet sheet, HttpServletRequest request) throws Exception {
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            // count++;
            TechRelate po = new TechRelate();
            // String rowValue = "";

            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setCode(
                                    DictionaryUtil.getCodeByNameType(request, cellValue, ConstantUtil.DIC_INFO_TECH));
                        }
                    }
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>技术领域信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    private static List<BasePO> bulidIndustry(Sheet sheet, HttpServletRequest request) throws Exception {
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            IndustryRelate po = new IndustryRelate();

            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setCode(DictionaryUtil.getCodeByNameType(request, cellValue,
                                    ConstantUtil.DIC_INFO_INDUSTRY));
                        }
                    }
                    // i++;
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>行业领域信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    /**
     * @Description 组织企业联系人信息
     * @param excelFile
     * @param dataType
     * @return
     * @throws Exception
     */
    private static List<BasePO> bulidContact(Sheet sheet, HttpServletRequest request) throws Exception {
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }

            // count++;

            ContactInfo po = new ContactInfo();
            // String rowValue = "";

            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setName(cellValue);
                        } else if (i == 3) {
                            po.setJob(DictionaryUtil.getCodeByNameType(request, cellValue,
                                    ConstantUtil.DIC_INFO_CONTACTJOB));
                        } else if (i == 4) {
                            po.setTel_1(cellValue);
                        } else if (i == 5) {
                            po.setTel_2(cellValue);
                        } else if (i == 6) {
                            po.setPhone(cellValue);
                        } else if (i == 7) {
                            po.setEmail(cellValue);
                        } else if (i == 8) {
                            if ("删除".equals(cellValue)) {
                                po.setEnable("2");
                            } else if ("正常".equals(cellValue)) {
                                po.setEnable("1");
                            } else {
                                po.setEnable(cellValue);
                            }
                        } else if (i == 9) {
                            po.setCreationUser(cellValue);
                        } else if (i == 10) {
                            po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 11) {
                            po.setLastModifyUser(cellValue);
                        } else if (i == 12) {
                            po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        }
                    }
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==联系人信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    /**
     * @Description 组织企业年报信息
     * @param sheet
     * @return
     * @throws Exception
     */
    private static List<BasePO> bulidAnnualReport(Sheet sheet) throws Exception {
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            AnnualReport po = new AnnualReport();
            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setTotalIncome(Double.valueOf(cellValue));
                        } else if (i == 3) {
                            po.setTotalAssets(Double.valueOf(cellValue));
                        } else if (i == 4) {
                            po.setTotalProfit(Double.valueOf(cellValue));
                        } else if (i == 5) {
                            po.setNetProfit(Double.valueOf(cellValue));
                        } else if (i == 6) {
                            po.setDevelopExpenses(Double.valueOf(cellValue));
                        } else if (i == 7) {
                            po.setScale(Double.valueOf(cellValue));
                        } else if (i == 8) {
                            if (cellValue.indexOf(".") != -1) {
                                po.setYear(cellValue.substring(0, cellValue.indexOf(".")));
                            } else {
                                po.setYear(cellValue);
                            }
                        } else if (i == 9) {
                            if ("删除".equals(cellValue)) {
                                po.setEnable("2");
                            } else if ("正常".equals(cellValue)) {
                                po.setEnable("1");
                            } else {
                                po.setEnable(cellValue);
                            }
                        } else if (i == 10) {
                            po.setCreationUser(cellValue);
                        } else if (i == 11) {
                            po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 12) {
                            po.setLastModifyUser(cellValue);
                        } else if (i == 13) {
                            po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        }
                    }

                    // i++;
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==年报信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    /**
     * @Description 组织股东信息
     * @param sheet
     * @return
     * @throws Exception
     */
    private static List<BasePO> bulidShareholder(Sheet sheet) throws Exception {
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            Shareholder po = new Shareholder();
            Shareholder po2 = new Shareholder();
            Shareholder po3 = new Shareholder();
            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(IDGenerator.getUUID());
                            po2.setId(IDGenerator.getUUID());
                            po3.setId(IDGenerator.getUUID());
                        } else if (i == 1) {
                            String eId = getEIdByCode(cellValue, basics);
                            if(StringUtils.isEmpty(eId)){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(eId);
                            po2.seteId(eId);
                            po3.seteId(eId);
                        } else if (i == 2) {
                            po.setName(cellValue);
                        } else if (i == 3) {
                            po.setQuantity(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                        } else if (i == 4) {
                            if(StringUtils.isNotEmpty(cellValue)){
                                po.setRatio(Double.valueOf(cellValue));
                            }
                        } 
                        
                        else if (i == 5) {
                            po2.setName(cellValue);
                        }  else if (i == 6) {
                            if(StringUtils.isNotEmpty(cellValue)){
                                po2.setQuantity(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            }
                        }  else if (i == 7) {
                            if(StringUtils.isNotEmpty(cellValue)){
                                po2.setRatio(Double.valueOf(cellValue));
                            }
                        } 
                        
                        else if (i == 8) {
                            po3.setName(cellValue);
                        }  else if (i == 9) {
                            if(StringUtils.isNotEmpty(cellValue)){
                                po3.setQuantity(Integer.valueOf(cellValue.substring(0, cellValue.indexOf("."))));
                            }
                        }  else if (i == 10) {
                            if(StringUtils.isNotEmpty(cellValue)){
                                po3.setRatio(Double.valueOf(cellValue));
                            }
                        } 
                        po.setEnable("1");
                        po2.setEnable("1");
                        po3.setEnable("1");
                        
                        /*else if (i == 5) {
                            if ("删除".equals(cellValue)) {
                                po.setEnable("2");
                            } else if ("正常".equals(cellValue)) {
                                po.setEnable("1");
                            } else {
                                po.setEnable(cellValue);
                            }
                        } else if (i == 6) {
                            po.setCreationUser(cellValue);
                        } else if (i == 7) {
                            po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 8) {
                            po.setLastModifyUser(cellValue);
                        } else if (i == 9) {
                            po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        }*/
                        
                        
                    }
                }
                pos.add(po);
                pos.add(po2);
                pos.add(po3);
                
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>股东信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    private static List<BasePO> bulidKnowledgeRight(Sheet sheet, HttpServletRequest request) throws Exception {
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            KnowledgeRight po = new KnowledgeRight();
            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setName(cellValue);
                        } else if (i == 3) {
                            po.setCode(cellValue);
                        } else if (i == 4) {
                            po.setPassTime(DateUtil.parseStrToDate(cellValue));
                        } else if (i == 5) {
                            if ("删除".equals(cellValue)) {
                                po.setEnable("2");
                            } else if ("正常".equals(cellValue)) {
                                po.setEnable("1");
                            } else {
                                po.setEnable(cellValue);
                            }
                        } else if (i == 6) {
                            po.setType(DictionaryUtil.getCodeByNameType(request, cellValue,
                                    ConstantUtil.DIC_INFO_KNOWLEDGETYPE));
                        } else if (i == 7) {
                            po.setCreationUser(cellValue);
                        } else if (i == 8) {
                            po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 9) {
                            po.setLastModifyUser(cellValue);
                        } else if (i == 10) {
                            po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        }
                    }
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>知识产权信息==>rowNum-->" + (count+1));
            }
            count++;
        }

        return pos;
    }

    private static List<BasePO> bulidProduction(Sheet sheet) throws Exception {
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;
        IEnterpriseBasicDao enterpriseBasicDao = SpringContextHolder.getBean("IEnterpriseBasicDao");
        List<EnterpriseBasic> basics = enterpriseBasicDao.findAllEnterprise();

        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            Production po = new Production();
            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }

                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            if(StringUtils.isEmpty(getEIdByCode(cellValue, basics))){
                                throw new Exception("没有企业基础");
                            }
                            po.seteId(getEIdByCode(cellValue, basics));
                        } else if (i == 2) {
                            po.setName(cellValue);
                        } else if (i == 3) {
                            po.setIncome(Double.valueOf(cellValue));
                        } else if (i == 4) {
                            if ("删除".equals(cellValue)) {
                                po.setEnable("2");
                            } else if ("正常".equals(cellValue)) {
                                po.setEnable("1");
                            } else {
                                po.setEnable(cellValue);
                            }

                        }

                        String format = SystemContext.getFormat();
                        if ("1".equals(format)) {
                            if (i == 5) {
                                po.setCreationUser(cellValue);
                            } else if (i == 6) {
                                po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                            } else if (i == 7) {
                                po.setLastModifyUser(cellValue);
                            } else if (i == 8) {
                                po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                            }
                        } else {
                            if (i == 5) {
                                po.setFwlyNames(cellValue);
                            }
                        }
                    }
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==>产品信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    private static List<BasePO> bulidProductionField(Sheet sheet) throws Exception {
        List<BasePO> pos = new ArrayList<BasePO>();
        int count = 0;

        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count == 0) {
                count++;
                continue;
            }
            // count++;
            ProductionFieldRelate po = new ProductionFieldRelate();
            try {
                //int a = 1 / 0;
                int len = row.getLastCellNum();
                for (int i = 0; i < len; i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = null;
                    if (cell != null) {
                        cellValue = convertCellToValue(cell, cell.getCellType());
                    }
                    if (i == 0) {
                        if (StringUtil.isEmpty(cellValue) || "(NULL)".equals(cellValue)) {
                            cellValue = IDGenerator.getUUID();
                        }
                    }
                    if (!StringUtil.isEmpty(cellValue) && !"(NULL)".equals(cellValue)) {
                        if (i == 0) {
                            po.setId(cellValue);
                        } else if (i == 1) {
                            po.setProductionId(cellValue);
                        } else if (i == 2) {
                            po.setFwlyCode(cellValue);
                        } else if (i == 3) {
                            po.setCreationUser(cellValue);
                        } else if (i == 4) {
                            po.setCreationTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        } else if (i == 5) {
                            po.setLastModifyUser(cellValue);
                        } else if (i == 6) {
                            po.setLastModifyTime(DateUtil.parseStrToDate(cellValue, DateUtil.FORMAT_2));
                        }
                    }
                }
                pos.add(po);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("==excelToDB==产品领域信息==>rowNum-->" + (count+1));
            }
            count++;
        }
        return pos;
    }

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param excelFile
     * @param dataType
     * @return
     * @throws Exception
     */
    public static List<BasePO> bulidBasePO(File excelFile, String dataType, HttpServletRequest request, int sheetNum)
            throws Exception {

        Sheet sheet = getSheetFromExcel(excelFile, sheetNum);

        List<BasePO> pos = null;
        if ("dictionary".equals(dataType)) {
            pos = bulidDictionary(sheet);
        } else if ("enterpriseBasic".equals(dataType)) {
            pos = bulidBasic(sheet, request);

        } else if ("aptitude".equals(dataType)) {
            pos = bulidAptitude(sheet, request);
        } else if ("tech".equals(dataType)) {
            pos = bulidTech(sheet, request);
        } else if ("industry".equals(dataType)) {
            pos = bulidIndustry(sheet, request);
        } else if ("contact".equals(dataType)) {
            pos = bulidContact(sheet, request);
        } else if ("annualReport".equals(dataType)) {
            pos = bulidAnnualReport(sheet);
        } else if ("shareholder".equals(dataType)) {
            pos = bulidShareholder(sheet);
        } else if ("knowledgeRight".equals(dataType)) {
            pos = bulidKnowledgeRight(sheet, request);
        } else if ("production".equals(dataType)) {
            pos = bulidProduction(sheet);
        } else if ("productionField".equals(dataType)) {
            pos = bulidProductionField(sheet);
        }

        return pos;

    }

}
