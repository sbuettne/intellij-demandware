package com.demandware.studio.projectWizard;

import com.demandware.studio.SFCCIcons;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class SFCCModuleType extends ModuleType<SFCCModuleBuilder> {

    private static final String ID = "SFCC_MODULE";

    public SFCCModuleType() {
        super(ID);
    }

    public static SFCCModuleType getInstance() {
        return (SFCCModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    @NotNull
    @Override
    public SFCCModuleBuilder createModuleBuilder() {
        return new SFCCModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return "SFCC";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "SFCC Studio Module";
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean isOpened) {
        return SFCCIcons.SFCC_ICON;
    }
}
