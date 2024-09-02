import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleInclusions;
import com.jwebmp.angular.forms.implementations.AngularFormScanModule;
import com.jwebmp.angular.forms.implementations.AngularTSOnBind;
import com.jwebmp.core.databind.IOnDataBind;

module com.jwebmp.angular.forms {

    exports com.jwebmp.angular.forms;
    exports com.jwebmp.angular.forms.enumerations;

    requires transitive com.jwebmp.core.angular;
    requires transitive com.jwebmp.core;

    provides IOnDataBind with AngularTSOnBind;
    provides IGuiceScanModuleInclusions with AngularFormScanModule;

    opens com.jwebmp.angular.forms to com.google.guice, com.jwebmp.core, com.jwebmp.core.angular, com.fasterxml.jackson.databind;
    opens com.jwebmp.angular.forms.implementations to com.google.guice, com.jwebmp.core, com.jwebmp.core.angular, com.fasterxml.jackson.databind;
}