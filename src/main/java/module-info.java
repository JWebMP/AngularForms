import com.jwebmp.angular.forms.implementations.AngularTSOnBind;

module com.jwebmp.angular.forms {

    exports com.jwebmp.angular.forms;
    exports com.jwebmp.angular.forms.enumerations;

    requires transitive com.jwebmp.core.angular;
    requires transitive com.jwebmp.core;
    requires com.jwebmp.plugins.bootstrap;

    provides IOnDataBind with AngularTSOnBind;
}