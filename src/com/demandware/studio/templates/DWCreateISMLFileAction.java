package com.demandware.studio.templates;

import com.demandware.studio.SFCCIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

public class DWCreateISMLFileAction extends CreateFileFromTemplateAction implements DumbAware {
    public DWCreateISMLFileAction() {
        super("ISML File", "Creates a new ISML file", SFCCIcons.ISML_ICON);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("ISML")
                .addKind("ISML File", SFCCIcons.ISML_ICON, "ISML File.isml");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "DWCreateISMLFile";
    }
}
