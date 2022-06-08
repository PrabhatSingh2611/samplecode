import { buildNameVariations } from "../../../engines/name-variations";
import { Schema, Generator } from "../../../meta-models";

const generate = (eventNames: string[], schema: Schema) => {
  const { model, ref, constant } = buildNameVariations(schema);

  const template = `import { castToString, Sort, useGetUrlSearchParams } from 'wdx';
import { ${model}Payload } from 'graphql-generated-types/types';
import {
    Get${model}For${model}DetailsPageQueryResult,
    useGet${model}For${model}DetailsPageQuery,
} from 'features/TODO/graphql/queries/get${model}For${model}DetailsPage.generated';
import { ${constant}_URL_QUERY_PARAMS_KEYS } from '../constants';

export const useGet${model}For${model}DetailsPage = (
    fetchPolicy = EFetchPolicy.CACHE_FIRST,
): [
    ${model}Payload | undefined,
    Get${model}For${model}DetailsPageQueryResult,
] => {
    const { id } = useGet${model}DetailsPageQueryParams();

    const get${model}Query = useGet${model}For${model}DetailsPageQuery({
        variables: {
            input: {
                id
            },
        },
        fetchPolicy: fetchPolicy,
    });

    const ${ref} = get${model}Query.data?.${ref};

    return [${ref}, get${model}Query];
};

interface IGet${model}DetailsPageQueryParams {
    id: string;
}

export function useGet${model}DetailsPageQueryParams(): IGet${model}DetailsPageQueryParams {
    const { id } = useGetUrlSearchParams(${constant}_URL_QUERY_PARAMS_KEYS);

    return { id: castToString(id) };
}`;

  return {
    template,
  };
};

export const ReactHookGetOne: Generator = {
  generate,
};
