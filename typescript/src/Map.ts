export default class CustomMap<T extends (string | number), V> {
    private items: Record<T, V>;

    constructor() {
        this.items = {} as Record<T, V>;
    }

    get(key: T): V | undefined {
        return this.items.hasOwnProperty(key) ? this.items[key] : undefined;
    }

    set(key: T, value: V): void {
        this.items[key] = value;
    }

    delete(key: T): V | undefined {
        if (this.items.hasOwnProperty(key)) {
            const deletedValue = this.items[key];
            delete this.items[key];
            return deletedValue;
        }
        return undefined;
    }

    size(): number {
        return Object.keys(this.items).length;
    }
}
