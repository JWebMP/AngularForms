import com.jwebmp.angular.forms.implementations.AngularTSOnBind;
import com.jwebmp.core.databind.IOnDataBind;

module com.jwebmp.angular.forms {

    exports com.jwebmp.angular.forms;
    exports com.jwebmp.angular.forms.enumerations;

    requires transitive com.jwebmp.core.angular;
    requires transitive com.jwebmp.core;

    provides IOnDataBind with AngularTSOnBind;
}