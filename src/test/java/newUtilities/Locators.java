package newUtilities;

import org.openqa.selenium.By;

public class Locators {

    // ── LOGIN PAGE ────────────────────────────────────────────────────────────
    public static final By LOGIN_GOOGLE_BUTTON      = By.xpath("//*[@id='q-app']/div/div/main/div/div/div[3]/div[1]/a/span[2]/span");
    public static final By LOGIN_EMAIL_FIELD        = By.xpath("//*[@id='identifierId']");
    public static final By LOGIN_NEXT_BUTTON        = By.xpath("//*[@id='identifierNext']/div/button/span");
    public static final By LOGIN_TWO_STEP_HEADING   = By.xpath("//*[@id='headingText']/span");

    // ── HEADER / COMMON NAVIGATION ────────────────────────────────────────────
    public static final By HEADER_HOME_BUTTON       = By.xpath("//*[@id='wrapper']/header/div/div/div[2]/a/div/img");
    public static final By HEADER_LOGO_WRAPPER      = By.xpath("//*[@id='wrapper']/header/div/div/div[2]/a/div/img");
    public static final By HEADER_SEARCH_BOX        = By.xpath("//*[@id='generic-search']");
    public static final By HEADER_SEARCH_RESULT_SPAN            = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[1]/div[1]/span");
    public static final By HEADER_SEARCH_CLIENT_REQUEST_DETAILS = By.xpath("//*[@id='wrapper']/div/div[3]/div[1]/div");

    // ── HUB SELECTOR ──────────────────────────────────────────────────────────
    public static final By HUB_SELECTOR_DROPDOWN    = By.xpath("//*[@id='wrapper']/header/div/div/div[3]/div[1]/div");
    public static final By HUB_OPTION_TEST_A1       = By.xpath("//*[text()='TEST-A1']");

    // ── HOME PAGE – FILTER DROPDOWNS ─────────────────────────────────────────
    // Points to the full dropdown container (div/div) — clickable anywhere on it,
    // and getText() returns only the selected-value text (the SVG arrow has no text).
    public static final By FILTER_SERVICE_TYPE      = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/span[2]/div/div");
    public static final By FILTER_STATUS            = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[2]/span[2]/div/div");
    public static final By FILTER_LEG_TYPE          = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[3]/span[2]/div/div");
    public static final By FILTER_REASON            = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[4]/span[2]/div/div");
    public static final By FILTER_HUB_SECTOR        = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[5]/span[2]/div/div");
    public static final By FILTER_HUB_SECTOR_ARROW  = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[5]/span[2]/div/div/div[2]/div/svg");
    public static final By FILTER_HUB_SECTOR_SELECTED = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[5]/span[2]/div/div/div[1]/div[1]");
    public static final By FILTER_COUNTRY_ZONE      = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[6]/span[2]/div/div");
    public static final By FILTER_COUNTRY_ZONE_ARROW = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[6]/span[2]/div/div/div[2]/div/svg");
    public static final By FILTER_COUNTRY_ZONE_SELECTED = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[6]/span[2]/div/div/div[1]/div[1]");
    public static final By FILTER_PICKUP_TYPE       = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[7]/span[2]/div/div");
    public static final By FILTER_PICKUP_TYPE_ARROW  = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[1]/div[7]/span[2]/div/div/div[2]/div/svg");
    public static final By DROPDOWN_SECOND_OPTION   = By.xpath("(//div[@role='option'])[2]");
    public static final By FILTER_RESET_BUTTON      = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]/div[2]/div[2]/button");

    public static final By[] ALL_FILTERS = {
            FILTER_SERVICE_TYPE, FILTER_STATUS, FILTER_LEG_TYPE,
            FILTER_REASON, FILTER_COUNTRY_ZONE, FILTER_HUB_SECTOR, FILTER_PICKUP_TYPE
    };

    public static final String[] ALL_FILTER_NAMES = {
            "Service Type", "Status", "Leg Type",
            "Reason", "Country Zone", "Hub Sector", "Pickup Type"
    };

    // ── FILTER OPTIONS ────────────────────────────────────────────────────────
    public static final By OPTION_DELIVERY          = By.xpath("//*[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='delivery']");
    public static final By OPTION_RESCHEDULED       = By.xpath("//*[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='rescheduled']");
    public static final By OPTION_AE                = By.xpath("//*[normalize-space(.)='AE']");
    public static final By OPTION_TEST_A1_S         = By.xpath("//*[contains(normalize-space(.),'TEST-A1-S')]");
    public static final By OPTION_REGULAR           = By.xpath("//*[translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='regular']");

    // ── HOME PAGE – RESULTS TABLE ─────────────────────────────────────────────
    public static final By RESULTS_TABLE            = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div[2]/table");
    public static final By RESULTS_TABLE_THEAD      = By.xpath("//*[@id='wrapper']//table/thead");
    public static final By RESULTS_TABLE_HEADERS    = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div[2]/table/thead/tr/th");
    public static final By RESULTS_TABLE_COL_11     = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div[2]/table/thead/tr/th[11]");
    public static final By RESULTS_TABLE_ROWS       = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div[2]/table/tbody/tr");
    public static final By RESULTS_TABLE_SERVICE_COL = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div[2]/table/tbody/tr/td[2]");
    public static final By RESULTS_TABLE_THIRD_ROW  = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div[2]/table/tbody/tr[3]/td[1]");

    // ── INLINE SEARCH (shared: home page + business-pickup page) ─────────────
    public static final By INLINE_SEARCH_BOX        = By.xpath("//*[@id='wrapper']//form/input");
    public static final By INLINE_SEARCH_CONTENT_AREA = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]");
    public static final By INLINE_SEARCH_FIRST_CELL = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[1]/div[1]/span");
    public static final By INLINE_SEARCH_CURRENT_LEG = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/div[1]/div");
    public static final By NO_DATA_MESSAGE          = By.xpath("//*[contains(text(),'Uh-oh') or contains(text(),'No Data') or contains(text(),'No data')]");
    public static final By HOME_CONTENT_AREA        = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[1]");

    // ── DETAIL PAGE – SELECTED ITEM ──────────────────────────────────────────
    public static final By DETAIL_STATUS_LABEL      = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div/span");
    public static final By DETAIL_TYPE_LABEL        = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[1]/div[2]/div/div[2]/div[4]/div[2]/span[2]");
    public static final By DETAIL_ATTEMPTS_BADGE    = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/span[2]");
    public static final By DETAIL_PACKAGES_HEADER   = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/div[2]/div[3]/div[2]/table/thead/tr/th[1]");
    public static final By DETAIL_PACKAGES_ROWS     = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/div[2]/div[3]/div[2]/table/tbody/tr");
    public static final By DETAIL_PACKAGES_FIRST_AWB = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/div[2]/div[3]/div[2]/table/tbody/tr[1]/td[3]");

    // ── BUSINESS PICKUP TASKS ─────────────────────────────────────────────────
    public static final By PICKUP_SEARCH_BOX        = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[2]/div/div[1]/div[2]/input");
    public static final By PICKUP_TASK_NUMBER_CELL  = By.xpath("//*[@id='wrapper']//table/tbody/tr/td[2]");
    public static final By PICKUP_SELECT_ALL_LABEL  = By.xpath("//*[@id='wrapper']//table/thead/tr/th[1]/div/label");
    public static final By PICKUP_DOWNLOAD_BUTTON   = By.xpath("//*[@id='wrapper']//a[contains(@href,'.csv') or contains(@download,'')]");
    public static final By PICKUP_PENDING_COUNT_CELL = By.xpath("//*[@id='wrapper']//table/tbody/tr/td[10]");
    public static final By PICKUP_CANCEL_BUTTON     = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div[4]/div[2]/table/tbody/tr/td[16]/div/button[2]");
    public static final By PICKUP_ERROR_TOAST       = By.xpath("//p[contains(@class,'toastCtr')]");
    public static final By PICKUP_NO_DATA_IMAGE     = By.xpath("//*[@id='wrapper']//img[not(ancestor::header)]");

    // ── REPORTS PAGE ──────────────────────────────────────────────────────────
    public static final By REPORTS_SEARCH_BOX       = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[1]/div/div/input");
    public static final By REPORTS_TPL_RESULT       = By.xpath("//*[@id='wrapper']//a[contains(normalize-space(.),'tpl_failure_report')]");
    public static final By REPORTS_RESULT_LINKS     = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/a");
    public static final By REPORTS_GENERATE_BUTTON  = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/form/div[2]/button");
    public static final By REPORTS_TABLE_FIRST_CELL = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[3]/div/table/tbody/tr[1]/td[1]");
    public static final By REPORTS_AGEING_LINK              = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/a[2]/div/div/p");
    public static final By REPORTS_HUB_INPUT                = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/form/div[1]/div/input");
    public static final By REPORTS_BUSINESS_PICKUP_PENDING  = By.xpath("//*[@id='wrapper']//a[contains(normalize-space(.),'business_pickup_tasks_pending')]");
    public static final By REPORTS_CIR_POTENTIAL_LINK       = By.xpath("//*[@id='wrapper']//a[contains(normalize-space(.),'cir_potential_user_assign')]");
    public static final By REPORTS_CIR_COUNTRY_INPUT        = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/form/div[1]/div[1]//input");
    public static final By REPORTS_CIR_LIMIT_INPUT          = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/form/div[1]/div[2]/input");
    public static final By REPORTS_TABLE_HEADER_FIRST       = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[3]/div/table/thead/tr/th[1]");
    public static final By REPORTS_CIR_DOWNLOAD_BUTTON      = By.xpath("//*[@id='wrapper']/div/div[3]/div[2]/div/div[2]/form/div[2]/button[2]");
    public static final By REPORTS_DOWNLOAD_SUCCESS_MSG     = By.xpath("//p[contains(@class,'toastCtr') and contains(@class,'success')]");
}
