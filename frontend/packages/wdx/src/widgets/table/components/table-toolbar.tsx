import React from 'react';
import { InputAdornment, SxProps, TextField } from '@mui/material';
import WIconify from '../../../components/iconify';
import { WDivider, WStack } from '../../../index';
import { Theme } from '@mui/material/styles';
import { WMenuItem } from '../../../components/menu';

export interface IWTableToolBar {
  spacing?: number;
  direction?: any;
  sx?: SxProps<Theme>;
  toolbarElements: React.ReactNode;
  withDivider?: boolean;
}

function WTableToolBar({
  spacing,
  direction,
  sx,
  toolbarElements,
  withDivider = true,
}: IWTableToolBar): JSX.Element {
  return (
    <>
      <WStack
        spacing={spacing || 2}
        direction={{ xs: 'column', sm: 'row', direction }}
        sx={{ py: 2.5, px: 3, ...sx }}
      >
        {toolbarElements}
      </WStack>
      {withDivider && <WDivider />}
    </>
  );
}

export interface ISearchBar {
  onSearchChange: (value: string) => void;
  searchValue: string;
}

WTableToolBar.SearchBar = ({
  onSearchChange,
  searchValue,
}: ISearchBar): JSX.Element => {
  return (
    <TextField
      fullWidth
      value={searchValue}
      onChange={(event: React.ChangeEvent<HTMLInputElement>) =>
        onSearchChange(event.target.value)
      }
      placeholder="Search user..."
      InputProps={{
        startAdornment: (
          <InputAdornment position="start">
            <WIconify
              icon={'eva:search-fill'}
              sx={{ color: 'text.disabled', width: 20, height: 20 }}
            />
          </InputAdornment>
        ),
      }}
    />
  );
};

interface IFilterOption {
  id: string;
  label: string;
}

interface IFilter {
  label: string;
  selectedFilter: string;
  onFilterChange: (value: string) => void;
  filterOptions: IFilterOption[];
}

WTableToolBar.Filter = ({
  label,
  selectedFilter,
  onFilterChange,
  filterOptions,
}: IFilter): JSX.Element => {
  return (
    <TextField
      fullWidth={true}
      select={true}
      label={label}
      value={selectedFilter}
      SelectProps={{
        MenuProps: {
          sx: { '& .MuiPaper-root': { maxHeight: 260 } },
        },
      }}
      sx={{
        maxWidth: { sm: 240 },
        textTransform: 'capitalize',
      }}
    >
      {filterOptions.map((option: any) => (
        <WMenuItem
          key={option.id}
          value={option.id}
          sx={{
            mx: 1,
            my: 0.5,
            borderRadius: 0.75,
            typography: 'body2',
            textTransform: 'capitalize',
          }}
          onClick={(): void => onFilterChange(option.id)}
        >
          {option.label}
        </WMenuItem>
      ))}
    </TextField>
  );
};

export default WTableToolBar;
