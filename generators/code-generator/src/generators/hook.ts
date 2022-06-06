import { buildNameVariations } from "../engines/name-variations";
import { Schema, Generator } from "../meta-models";

const generate = (eventNames: string[], schema: Schema) => {
  const { model, models, refs, constants } = buildNameVariations(schema);

  const template = `// <<< QUERIES >>>

// Multiple (Connection)
export const useGet${models}For${models}ListPage = (
    fetchPolicy = EFetchPolicy.CACHE_FIRST,
): [
    ${model}ConnectionPayload | undefined,
    Get${models}For${models}ListQueryResult,
] => {
    const { pageNumber, sort, itemsPerPage, query } =
        useGet${models}ListPageQueryParams();

    const whereQuery = query.length >= MIN_COUNT_OF_SYMBOLS_TO_SEARCH ? query : undefined;

    const get${models}Query = useGet${models}For${models}ListPageQuery({
        variables: {
            input: {
                where: { whereQuery },
                pagination: { pageNumber: Number(pageNumber), itemsPerPage: Number(itemsPerPage) },
                sort: [sort],
            },
        },
        fetchPolicy: fetchPolicy,
    });

    const ${refs} = get${models}Query.data?.${refs};

    return [${refs}, get${models}Query];
};

interface IGet${models}ListPageQueryParams {
    pageNumber: number;
    sort: Sort;
    itemsPerPage: number;
    query: string;
}

export function useGet${models}ListPageQueryParams(): IGet${models}ListPageQueryParams {
    const urlQueryParamsValues = useGetUrlSearchParams(${constants}_URL_QUERY_PARAMS_KEYS);

    const {
        pageNumber,
        sort = Sort.DESC,
        itemsPerPage = String(TABLE_DEFAULT_ROWS_COUNT),
        query,
    } = urlQueryParamsValues;

    return {
        pageNumber: Number(castToString(pageNumber, '1')),
        sort: sort as Sort,
        itemsPerPage: Number(itemsPerPage),
        query: castToString(query),
    };
}`;

  return {
    template,
  };
};

export const HookGenerator: Generator = {
  generate,
};
