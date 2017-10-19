/**
 * Copyright (C) 2017 Newland Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newlandframework.rpc.event;

import com.newlandframework.rpc.jmx.ModuleMetricsVisitor;

import java.util.Observable;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:InvokeFailObserver.java
 * @description:InvokeFailObserver功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2017/10/12
 */
public class InvokeFailObserver extends AbstractInvokeObserver {
    private Throwable error;

    public InvokeFailObserver(InvokeEventBusFacade facade, ModuleMetricsVisitor visitor, Throwable error) {
        super(facade, visitor);
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public void update(Observable o, Object arg) {
        if ((AbstractInvokeEventBus.ModuleEvent) arg == AbstractInvokeEventBus.ModuleEvent.INVOKE_FAIL_EVENT) {
            super.getFacade().fetchEvent(AbstractInvokeEventBus.ModuleEvent.INVOKE_FAIL_EVENT).notify(super.getVisitor().getInvokeFailCount(), super.getVisitor().incrementInvokeFailCount());
            super.getFacade().fetchEvent(AbstractInvokeEventBus.ModuleEvent.INVOKE_FAIL_STACKTRACE_EVENT).notify(null, error);
        }
    }
}
