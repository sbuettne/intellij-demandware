package com.demandware.studio.templates;

import com.demandware.studio.SFCCIcons;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

public class DWCreateDSFileAction extends CreateFileFromTemplateAction implements DumbAware {

    public DWCreateDSFileAction() {
        super("DS File", "Creates a DS file", SFCCIcons.DS_ICON);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("DS File")
                .addKind("DS File", SFCCIcons.DS_ICON, "DS File.ds")
                .addKind("DS Script Node File", SFCCIcons.DS_ICON, "DS Script Node File.ds");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "DWCreateDSFile";
    }
}
