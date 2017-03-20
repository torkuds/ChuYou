/*
 * Copyright © YOLANDA. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jueda.ndian.nohttp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jueda.ndian.R;

/**
 * Created in Oct 23, 2015 1:19:04 PM.
 *
 * @author YOLANDA;
 */
public class  WaitDialog {
    public Dialog dialog;

    public WaitDialog(Context context) {
        //加载弹窗布局
        LayoutInflater inflater=LayoutInflater.from(context);
        RelativeLayout layout=(RelativeLayout) inflater.inflate(R.layout.loading, null);
        //创建dialog
        dialog=new AlertDialog.Builder(context,R.style.add_dialog).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        //给dialog赋值view
        dialog.getWindow().setContentView(layout);
    }
    public void dismiss(){
        dialog.dismiss();
    }
    public void show(){
        dialog.show();
    }
    public boolean isShowing(){
        return dialog.isShowing();
    }
}
