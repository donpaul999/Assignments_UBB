import { observer } from "mobx-react";
import { FC, useContext, useEffect } from "react";
import { Book } from "../../accessors/types";
import { DataProviderContext } from "./data-provider-store";

type BookParameterFunction = (book: Book) => Promise<void>;

export interface WithDataProvider {
    availableBooks: Book[];
    addBook: BookParameterFunction;
    updateBook: BookParameterFunction;
    deleteBook: BookParameterFunction;
}

function withDataProvider<T extends WithDataProvider>(WrappedComponent: FC<T>) {
    const ComponentWithDataProvider = (props: Omit<T, keyof WithDataProvider>) => {
        const dataProviderStore = useContext(DataProviderContext);

        useEffect(() => {
            dataProviderStore.initialize();
            return dataProviderStore.unsubscribeToChanges();
        }, [dataProviderStore]);

        return <WrappedComponent {...(props as T)} {...dataProviderStore} />
    }

    return observer(ComponentWithDataProvider);
}

export default withDataProvider;
