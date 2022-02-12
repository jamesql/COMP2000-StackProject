package stack;

import java.util.Arrays;

public class Stack<T> implements StackInterface<T> {
	private static final int DEFAULT_LIMIT = 100;
	private T[] stack;
	private int limit;
	private int entries = 0;

	public Stack() {
		this(DEFAULT_LIMIT);
	}

	@SuppressWarnings("unchecked")
	public Stack(int limit) {
		this.limit = limit;
		this.stack = (T[]) new Object[limit];
	}

	private void doubleLimit() {
		limit *= 2;
		stack = Arrays.copyOf(stack, limit);
	}

	@Override
	public void push(T anEntry) {
		stack[entries++] = anEntry;
		if (entries == limit)
			doubleLimit();
	}

	@Override
	public T pop() {
		T temp = stack[entries - 1];
		stack[--entries] = null;
		return temp;
	}

	@Override
	public T peek() {
		return stack[entries - 1];
	}

	@Override
	public boolean isEmpty() {
		return entries == 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		stack = (T[]) new Object[limit];
		entries = 0;
	}
}
