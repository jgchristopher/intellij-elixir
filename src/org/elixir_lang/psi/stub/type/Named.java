package org.elixir_lang.psi.stub.type;

import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.NamedStubBase;
import org.elixir_lang.psi.stub.index.AllName;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public abstract class Named<S extends NamedStubBase<T>, T extends PsiNameIdentifierOwner> extends Element<S, T> {
    public Named(@NonNls @NotNull String debugName) {
        super(debugName);
    }

    @Override
    public void indexStub(@NotNull final S stub, @NotNull final IndexSink sink) {
        String name = stub.getName();

        if (name != null) {
            sink.occurrence(AllName.KEY, name);
        }

        if (stub instanceof org.elixir_lang.psi.stub.call.Stub) {
            org.elixir_lang.psi.stub.call.Stub callStub = (org.elixir_lang.psi.stub.call.Stub) stub;
            Set<String> canonicalNameSet = callStub.canonicalNameSet();

            for (String canonicalName : canonicalNameSet) {
                if (!canonicalName.equals(name)) {
                    sink.occurrence(AllName.KEY, canonicalName);
                }
            }
        }
    }

    @NotNull
    public String getExternalId() {
        return "elixir." + super.toString();
    }
}
