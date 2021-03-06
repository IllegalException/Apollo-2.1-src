//Decomped by XeonLyfe

package org.spongepowered.asm.mixin.throwables;

public class ClassAlreadyLoadedException extends MixinException
{
    private static final long serialVersionUID = 1L;
    
    public ClassAlreadyLoadedException(final String message) {
        super(message);
    }
    
    public ClassAlreadyLoadedException(final Throwable cause) {
        super(cause);
    }
    
    public ClassAlreadyLoadedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
