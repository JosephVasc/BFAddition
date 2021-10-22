interface Instructions{
    fun accept(visitor: BFElementVisitor)
}

interface BFElementVisitor {
    fun visit(left: Left)
    fun visit(right: Right)
    fun visit(increment: Increment)
    fun visit(decrement: Decrement)
    fun visit(output: Output)
    fun visit(input: Input)
}

class Left : Instructions {

    override fun accept(visitor: BFElementVisitor) {
        /*
       * accept(BFElementVisitorVisitor) in Wheel implements
       * accept(BFElementVisitorVisitor) in BFElementVisitor, so the call
       * to accept is bound at run time. This can be considered
       * the *first* dispatch. However, the decision to call
       * visit(Wheel) (as opposed to visit(Engine) etc.) can be
       * made during compile time since 'this' is known at compile
       * time to be a Wheel. Moreover, each implementation of
       * BFElementVisitorVisitor implements the visit(Wheel), which is
       * another decision that is made at run time. This can be
       * considered the *second* dispatch.
       */
        visitor.visit(this)
    }

}

class Input : Instructions {
    override fun accept(visitor: BFElementVisitor) {
        visitor.visit(this)
    }
}
class Output : Instructions {
    override fun accept(visitor: BFElementVisitor) {
        visitor.visit(this)
    }
}
class Right : Instructions {
    override fun accept(visitor: BFElementVisitor) {
        visitor.visit(this)
    }
}

class Increment : Instructions {
    override fun accept(visitor: BFElementVisitor) {
        visitor.visit(this)
    }
}

class Decrement : Instructions {
    override fun accept(visitor: BFElementVisitor) {
        visitor.visit(this)
    }

}

class Loop : Instructions {
    override fun accept(visitor: BFElementVisitor) {

    }
}


fun main(args: Array<String>) {
    print("weewee")


}